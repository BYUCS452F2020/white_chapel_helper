package Services;

import DAOs.ConnectionDAO;
import DAOs.PossibleLocationDAO;
import Models.Connection;
import Models.Investigation;
import Models.Possible_Location;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class PossibleLocationService {

  //JackMoveService jack_moves = new JackMoveService();
  PossibleLocationDAO locationDAO = new PossibleLocationDAO();
  ConnectionDAO connectionDAO = new ConnectionDAO();

  // Jack has moved, update the PossibleLocationTable to include all possible nodes for this new turn
  //turn parameter is the CURRENT turn, there should be no possible locations for the CURRENT turn...yet
  public void jackMovedUpdateTable(int turn, String moveType){

    // get all nodes for previous turn
    // for each node
    // get all possible NEW locations, starting from this node
    // update DB with all possible new locations

    List<Possible_Location> old_locations;
    Vector<Integer> new_locations = new Vector<>();
    Possible_Location curr_location;

    //go through all the possible locations from last turn
    old_locations = locationDAO.getPossLocFromTurn(turn - 1);
    Iterator iter = old_locations.iterator();
    while (iter.hasNext()){
      //TODO check to see if iter.next is of class possible_location
      //for each curr_location, make a list of connections which we can iterate through
      curr_location = (Possible_Location) iter.next();
      List<Connection> street_connections = connectionDAO.getConnectionsFromNodeAndTurnType
              (curr_location.getNode(), moveType);

      //for each connection, we check if it's contained in the vector new_locations
      //if not, we add it
      Iterator conn_iter = street_connections.iterator();
      while (conn_iter.hasNext()){
        //TODO check to see if conn_iter.next is of class Connection
        Connection curr_connection = (Connection) conn_iter.next();
        if (!new_locations.contains(curr_connection.getTo_node())){
          new_locations.add(curr_connection.getTo_node());
        }
      }
    }

    for (int i = 0; i < new_locations.size(); i++){
      locationDAO.insertPossibleLoc(turn, new_locations.elementAt(i));
    }

  }

  // a player has investigated a node, update the PossibleLocations table
  public void investigatedUpdateTable(Investigation investigation){
    //TODO
  }

  public List<Possible_Location> getAllLocByTurn(int turn){
    return locationDAO.getPossLocFromTurn(turn);
  }

  public List<Possible_Location> getAllLoc(){
    return locationDAO.getAllPossLoc();
  }
}
