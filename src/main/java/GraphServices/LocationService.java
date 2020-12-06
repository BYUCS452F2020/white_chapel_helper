package GraphServices;

import DAOs_Graph.LocationDAO;
import Models.Investigation;
import Models.Possible_Location;

import java.util.List;

public class LocationService {
  LocationDAO dao = new LocationDAO();
  public void jackMoves(int destination, String move_type) throws Exception{
    dao.setJackVisitedTrue(destination);
  }

  public void setJackStartNode(int start) throws Exception {
    dao.setJackVisitedTrue(start);
  }

  public boolean isValidMove(int starting, int destination, String conn_type){
	return false;
  }

  public boolean isValidNode(int node){
//    MATCH (node:Location) where node.Number = 99 return node
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
