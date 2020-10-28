package Services;

import DAOs.Database;
import Models.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionService {
  public boolean isValidMove(int starting, int destination, String conn_type){

    Database db = new Database();

    boolean success = false;
    boolean valid_move = false;
    try {
      java.sql.Connection connection = db.getConnection();
      //create the string for the query
      String query = "SELECT * FROM Connections WHERE from_node = (?)" +
              "AND to_node = (?)" +
              "AND connection_type = (?)" ;
      //make the prepared statment and add values
      PreparedStatement stmt = connection.prepareStatement(query);
      stmt.setInt(1, starting);
      stmt.setInt(2, destination);
      stmt.setString(3, conn_type);

      //we don't really need the result set, we just need to see if it has any values'
      ResultSet rSet = stmt.executeQuery();
      success = true;
      if (!rSet.next()){
        valid_move = false;
      }
      else {
        valid_move = true;
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      db.closeConnection(success);
    }
    return valid_move;

  }

  public List<Connection> getShortestPath(int starting, int destination){
    return null; //TODO
  }

  public int getShortestDistance(int starting, int destination){
    return 1; //TODO
  }
}
