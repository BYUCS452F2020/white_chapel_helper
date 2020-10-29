import Models.Possible_Location;
import Services.BoardService;
import Services.JackMoveService;
import Services.PossibleLocationService;

import java.util.List;
import java.util.Scanner;

public class Main {
  private int turn = 0;
  public static void main(String[] args) throws Exception {
	// TODO setup the code to actually play a game
	// should call only the service classes
    Scanner in = new Scanner(System.in);
    BoardService boardService = new BoardService();
    boardService.loadDummyDataModerate();
    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
    System.out.println("                            Welcome to your Letters from White Chapel Assistant                        ");
    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

    String game_type = null;
    int node = 0;
    boolean jackWins = false;

    setGameType(game_type, in);
    setJackStartNode(node, in);

    System.out.println("\n");
    for(int turn=1; turn <= 15 && !jackWins; turn++){
      System.out.println("Turn " + turn);
      jackWins = jackMoves(node, in); // Moves Jack, checks to see whether Jack has reached his lair
      if(!jackWins && turn < 15) printPossLoc(turn); // If Jack hasn't reached his lair by the final turn, constables win
    }
    if(!jackWins) System.out.println("No more turns: constables win this round!");
    System.out.println("Thanks for playing!");
  }

  private static void setGameType(String game_type, Scanner in){
    boolean waiting_for_input = true;
    System.out.println("Do you want to control Jacks moves, or do you want us to? (Enter CONTROL or AI)");
    while(waiting_for_input) {
      game_type = in.nextLine();
      if(game_type.equalsIgnoreCase("control")) {
        System.out.println("You have chosen control");
        waiting_for_input = false;
      } else {
        System.out.println("only control is implemented right now. Try again");
      }
    }
  }

  private static void setJackStartNode(int node, Scanner in){
    boolean waiting_for_input = true;
    while(waiting_for_input) {
      System.out.println("What node does Jack start on?");
      node = in.nextInt();
      try {
        JackMoveService jackMoveService = new JackMoveService();
        jackMoveService.setJackStartNode(node);
        waiting_for_input = false;
      }catch(Exception e){
        System.out.println("Node: " + node  + " does not exist. Try again");
      }
    }
  }

  private static boolean jackMoves(int node, Scanner in){
    boolean waiting = true;
    boolean jackWins = false;
    JackMoveService jackMoveService = new JackMoveService();
    while(waiting) {
      System.out.println("Where does Jack move?");
      node = in.nextInt();
      in.nextLine();
      try {
        jackMoveService.jackMoves(node, "normal");
        waiting = false;
        jackWins = jackAtLair(in);
      } catch(Exception e) {
        System.out.println("Invalid move. Try Again");
      }
    }
    return jackWins;
  }

  private static void printPossLoc(int turn){
    PossibleLocationService possibleLocationService = new PossibleLocationService();
    List<Possible_Location> allLoc = possibleLocationService.getAllLocByTurn(turn);
    StringBuilder sb = new StringBuilder();
    for(Possible_Location possLoc: allLoc){
      sb.append(possLoc.getNode());
      sb.append(", ");
    }
    sb.append("\n");

    System.out.println("Jack could be at any of these nodes...");
    System.out.println(sb.toString());
  }

  private static boolean jackAtLair(Scanner in) {
    boolean jackWins = false;
    boolean gotResponse = false;
    String winStatus;
    while (!gotResponse) {
      System.out.println("Is Jack at his lair? (Y/N)");
      winStatus = in.nextLine();
      if (winStatus.equalsIgnoreCase("Y")) {
        System.out.println("Jack wins this round!");
        jackWins = true;
        gotResponse = true;
      }
      else if (winStatus.equalsIgnoreCase("N")) gotResponse = true;
      else System.out.println("Please enter \"Y\" or \"N\"");
    }
    return jackWins;
  }
}
