package DAOs;

import Models.Possible_Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PossibleLocationDAO {
  private final Database db = new Database();

  public Possible_Location getPossibleLoc(int turn, int node){
    Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Possible_Locations WHERE turn= " + turn + " and node = " + node;
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
	    turn = rs.getInt("turn");
	    node = rs.getInt("node");
	    success = true;
		return new Possible_Location(turn, node);
	  }
	} catch(Exception e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
	return null;
  }

  public Boolean insertPossibleLoc(int turn, int node){
    Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Possible_Locations VALUES( ?,?)");
	  ps.setInt(1, turn);
	  ps.setInt(2, node);

	  int i = ps.executeUpdate();
	  if( i==1){
	    success = true;
	    return success;
	  }

	} catch(DataAccessException | SQLException e) {
	  e.printStackTrace();
	} finally {
	  db.closeConnection(success);
	}
	return success;
  }

  public Boolean insertPossibleLoc(Possible_Location possible_location){
    return insertPossibleLoc(possible_location.getTurn(), possible_location.getNode());
  }

  public List<Possible_Location> getPossLocFromTurn(int turn){
    List<Possible_Location> possible_locations = new ArrayList<>();
    Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Possible_Locations WHERE turn = " + turn;
	  ResultSet rs = stmt.executeQuery(query);

	  while(rs.next()){
		int turn_temp = rs.getInt("turn");
		int node_temp = rs.getInt("node");
		possible_locations.add(new Possible_Location(turn_temp, node_temp));
	  }
	  success = true;
	} catch(Exception e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
	return possible_locations;
  }

  public List<Possible_Location> getAllPossLoc(){
	List<Possible_Location> possible_locations = new ArrayList<>();
	Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Possible_Locations";
	  ResultSet rs = stmt.executeQuery(query);

	  while(rs.next()){
		int turn_temp = rs.getInt("turn");
		int node_temp = rs.getInt("node");
		possible_locations.add(new Possible_Location(turn_temp, node_temp));
	  }
	  success = true;
	} catch(Exception e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
	return possible_locations;
  }

  public int getSoonestReached(int node){
	  int turn_num = -1;
	  Boolean success = false;
  	try {
  		java.sql.Connection connection = db.getConnection();
  		Statement stmt = connection.createStatement();
  		String query = "SELECT min(turn) as earliest_turn FROM Possible_Locations where node = " + node ;
  		ResultSet rs = stmt.executeQuery(query);

  		if(rs.next()){
  			turn_num = rs.getInt("earliest_turn");
			turn_num = rs.wasNull() ? -1 : turn_num;
  		}
  		success = true;

  	} catch(Exception e) {
  		e.printStackTrace();
  	}finally {
  		db.closeConnection(success);
  	}
  	return turn_num;
  }



  public Boolean clear(){
	Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  PreparedStatement ps = connection.prepareStatement("DELETE FROM Possible_Locations");

	  int i1 = ps.executeUpdate();
	  success = true;
	} catch(DataAccessException | SQLException e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
	return success;
  }
}
