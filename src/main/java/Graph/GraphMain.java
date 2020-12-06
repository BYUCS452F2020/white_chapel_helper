package Graph;

import Graph.DAOs_Graph.databaseGraph;
import Graph.GraphServices.*;
import org.neo4j.driver.Driver;

import java.util.List;
import java.util.Scanner;

public class GraphMain {
  private Driver driver;
  private LocationService service;
  private int startNode;
  private int currentLoc;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    GraphMain game = new GraphMain();
    game.setupDataBase();

    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
    System.out.println("                            Welcome to your Letters from White Chapel Assistant                        ");
    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

    boolean jackWins = false;

    String gameType = game.setGameType(in);
    game.setJackStartNode(in);

    int lair = game.setJackLair(in);

    System.out.println("\n");
    for(int turn=1; turn <= 15 && !jackWins; turn++){
      System.out.println("Turn " + turn);
      jackWins = game.jackMoves(in, lair); // Moves Jack, checks to see whether Jack has reached his lair
      if(!jackWins) {
        game.printPossLoc(turn); // If Jack hasn't reached his lair by the final turn, constables win
        game.conductInvestigations(turn, in);
      }
    }
    if(!jackWins) System.out.println("No more turns: constables win this round!\n Thanks for playing");
    else{
      System.out.println("Thanks for slaying!");
    }
  }

  private int setJackLair(Scanner in) {
    boolean waitingForInput = true;
    int node = 0;
    while(waitingForInput) {
      System.out.println("What node is Jack's lair?");
      String temp = in.nextLine();
      try {
        node = Integer.parseInt(temp);
        waitingForInput = !service.isValidNode(node);
      }catch(Exception e){
        System.out.println("Node " + temp  + " does not exist. Try again");
      }
    }
    return node;
  }

  private void setupDataBase(){
    databaseGraph db = new databaseGraph();
    db.clear_graph();
    driver = db.getDriver();
    BoardService boardService = new BoardService(driver);
    try {
      boardService.loadDummyDataModerate();
      service = new LocationService(driver);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private String setGameType(Scanner in){
    System.out.println("Do you want to control Jacks moves, or do you want us to? (Enter CONTROL or AI)");
    while(true) {
      String gameType = in.nextLine();
      if(gameType.equalsIgnoreCase("control")) {
        System.out.println("You have chosen control");
        return "CONTROL";
      } else {
        System.out.println("only control is implemented right now. Try again");
      }
    }
  }

  private void setJackStartNode(Scanner in){
    boolean waitingForInput = true;
    while(waitingForInput) {
      System.out.println("What node does Jack start on?");
      String temp = in.nextLine();
      try {
        int node = Integer.parseInt(temp);
        service.setJackStartNode(node);
        startNode = node;
        this.updateCurrentLoc(node);
        waitingForInput = false;
      }catch(Exception e){
        System.out.println("Node " + temp + " does not exist. Try again");
      }
    }
  }

  private boolean jackMoves(Scanner in, int lair){
    boolean waiting = true;
    boolean jackWins = false;
    while(waiting) {
      System.out.println("Where does Jack move?");
      String temp = in.nextLine();
      try {
        int node = Integer.parseInt(temp);
        service.jackMoves(currentLoc, node, "normal");
        this.updateCurrentLoc(node);
        waiting = false;
        jackWins = jackAtLair(lair, node);
      } catch(Exception e) {
        System.out.println("Invalid move. Try Again");
      }
    }
    return jackWins;
  }

  private void printPossLoc(int turn){
    List<Integer> allLoc = service.getAllLocByTurn(startNode, turn);
    StringBuilder sb = new StringBuilder();
    for(Integer possLoc: allLoc){
      sb.append(possLoc.toString());
      sb.append(", ");
    }
    sb.append("\n");

    System.out.println("Jack could be at any of these nodes...");
    System.out.println(sb.toString());
  }

  private static boolean jackAtLair(int lair, int loc) {
    if(lair == loc){
      System.out.println("Jack wins this round!");
    }
    return lair == loc;
  }

  private static boolean wouldYouLikeToInvestigate(Scanner in){
    String response;
    while(true){
      System.out.println("Would you like to investigate a node? (Y/N)");
      response = in.nextLine();

      if(response.equalsIgnoreCase("n")){
        return true;
      }
      else if(response.equalsIgnoreCase("y")){
        return false;
      }else{
          System.out.println("Please enter \"Y\" or \"N\"");
      }
    }
  }

  private void conductInvestigations(int turn, Scanner in) {
    boolean passTurn = wouldYouLikeToInvestigate(in); //passTurn updated in this func

    while (!passTurn) {
      System.out.println("Which node will you investigate?");
      String temp = in.nextLine();
      try {
        int invNode = Integer.parseInt(temp);
        boolean jackWasHere = service.investigate(invNode, turn);
        if(jackWasHere) System.out.println("A clue was found! Recorded in database.");
        else System.out.println("No clue found. Recorded in database.");

        passTurn = wouldYouLikeToInvestigate(in);
      }catch(Exception e){
        System.out.println("Node " + temp + " does not exist. Try again.");
      }
    }
  }

  private void updateCurrentLoc(int currentLoc){
    this.currentLoc = currentLoc;
  }
}
