package Relational.Services;

import Relational.DAOs.ConnectionDAO;
import Relational.DAOs.JackMoveDAO;
import Relational.DAOs.PossibleLocationDAO;

public class JackMoveService {

  PossibleLocationDAO locations_dao =  new PossibleLocationDAO();
  JackMoveDAO jack_move_dao = new JackMoveDAO();
  ConnectionService connectionService = new ConnectionService();
  ConnectionDAO connectionDAO = new ConnectionDAO();

  public void jackMovesRandom(){
    //TODO
  }

  public void jackMoves(int destination, String move_type) throws Exception{
    PossibleLocationService possibleLocationService = new PossibleLocationService();
    int current_node = jack_move_dao.getCurrentJackLocation();
    int current_turn = jack_move_dao.getTurn();
    current_turn ++;

    if(connectionService.isValidMove(current_node, destination, "street")){
      jack_move_dao.insertJackMove(current_turn, destination, move_type);
    }else{
      throw new Exception("Invalid Move");
    }
    possibleLocationService.jackMovedUpdateTable(current_turn, "street");
  }

  public int getNumAlleysUsed(){
    return 1; //TODO
  }

  public int getNumCarriagesUsed(){
    return 1; //TODO
  }

  public void setJackStartNode(int start) throws Exception {
    if(connectionDAO.isValidNode(start)){
      //I gave the start node a connection type of street, shouldn't matter though -Nate
      jack_move_dao.insertJackMove(0, start, "normal");
      locations_dao.insertPossibleLoc(0, start);
    }else{
      throw new Exception("invalid node");
    }
  }
}
