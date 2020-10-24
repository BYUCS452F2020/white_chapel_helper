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
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Possible_Locations WHERE turn= " + turn + " and node = " + node;
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
	    turn = rs.getInt("turn");
	    node = rs.getInt("node");
		return new Possible_Location(turn, node);
	  }
	} catch(Exception e) {
	  e.printStackTrace();
	}
	return null;
  }

  public Boolean insertPossibleLoc(int turn, int node){
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Possible_Locations VALUES( ?,?)");
	  ps.setInt(1, turn);
	  ps.setInt(2, node);

	  int i = ps.executeUpdate();
	  if( i==1){
	    return true;
	  }

	} catch(DataAccessException | SQLException e) {
	  e.printStackTrace();
	}
	return false;
  }

  public Boolean insertPossibleLoc(Possible_Location possible_location){
    return insertPossibleLoc(possible_location.getTurn(), possible_location.getNode());
  }

  public List<Possible_Location> getPossLocFromTurn(int turn){
    List<Possible_Location> possible_locations = new ArrayList<>();
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
	} catch(Exception e) {
	  e.printStackTrace();
	}
	return possible_locations;
  }

  public List<Possible_Location> getAllPossLoc(){
	List<Possible_Location> possible_locations = new ArrayList<>();
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
	} catch(Exception e) {
	  e.printStackTrace();
	}
	return possible_locations;
  }
}
