package GraphServices;

import Models.Investigation;
import Models.Possible_Location;

import java.util.List;

public class LocationService {
  public void jackMoves(int destination, String move_type) throws Exception{
  }

  public void setJackStartNode(int start) throws Exception {

  }

  public boolean isValidMove(int starting, int destination, String conn_type){
	return false;
  }

  public boolean isValidNode(int node){
    return true;
  }

  public Boolean investigate(int node, int turn){
	return null;
  }

  public void jackMovedUpdateTable(int turn, String moveType){

  }

  // a player has investigated a node, update the PossibleLocations table
  public void investigatedUpdateTable(Investigation investigation){

  }

  public List<Possible_Location> getAllLocByTurn(int turn){
    return null;
  }
}
