package Graph.GraphServices;

import Graph.DAOs_Graph.LocationDAO;
import Relational.Models.Investigation;
import org.neo4j.driver.Driver;

import java.util.List;

public class LocationService {
  private LocationDAO dao;
  private Driver driver;

  public LocationService(Driver driver) {
	this.driver = driver;
	this.dao = new LocationDAO(driver);
  }

  public void jackMoves(int start, int destination, String move_type) throws Exception{
    if(dao.isValidMove(start, destination)){
      dao.setJackVisitedTrue(destination);
	}else{
      throw new Exception("Invalid Move");
	}
  }

  public void setJackStartNode(int start) throws Exception {
    if(dao.isValidNode(start)) {
	  dao.setJackVisitedTrue(start);
	}else{
      throw new Exception("invalid node");
	}
  }

  public boolean isValidMove(int starting, int destination, String conn_type){
	return dao.isValidMove(starting, destination);
  }

  public boolean isValidNode(int node){
    return dao.isValidNode(node);
  }

  public Boolean investigate(int node, int turn){
	return dao.checkForClue(node, turn);
  }

  public List<Integer> getAllLocByTurn(int startNode, int turn){
    return dao.getAllLocByTurn(startNode, turn);
  }
}
