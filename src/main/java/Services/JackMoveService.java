package Services;

import DAOs.JackMoveDAO;
import DAOs.PossibleLocationDAO;
import Models.MoveType;

public class JackMoveService {

  PossibleLocationDAO locations_dao =  new PossibleLocationDAO();
  JackMoveDAO jack_move_dao = new JackMoveDAO();

  public void jackMovesRandom(){
    //TODO
  }

  public void jackMoves(int destination){
    //TODO
  }

  public int getNumAlleysUsed(){
    return 1; //TODO
  }

  public int getNumCarriagesUsed(){
    return 1; //TODO
  }

  public void setJackStartNode(int start){

    //I gave the start node a connection type of street, shouldn't matter though -Nate
    jack_move_dao.insertJackMove(0, start, "normal");
    locations_dao.insertPossibleLoc(0, start);

  }
}
