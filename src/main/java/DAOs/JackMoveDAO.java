package DAOs;

import Models.Jack_Move;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JackMoveDAO {
  private Database db = new Database();

  public Jack_Move getJackMove(int turn){
    boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Jack_Moves WHERE turn= " + turn;
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
	    String move_type = rs.getString("move_type");
	    int dest_node = rs.getInt("destination_node");
	    success = true;

		return new Jack_Move(turn, dest_node, move_type);
	  }

	} catch(Exception e) {
	  e.printStackTrace();
	} finally {
	  db.closeConnection(success);
	}
	return null;
  }

	public Boolean jackWasHere(int node){
		// Select all from JackMove table where dest_node = node;
		// If null return false
		// Else return true.
		boolean jackVisited = false;
		boolean success = false;
		try {
			java.sql.Connection connection = db.getConnection();
			Statement stmt = connection.createStatement();
			String query = "SELECT * FROM Jack_Moves WHERE destination_node= " + node;
			ResultSet rs = stmt.executeQuery(query);

			if(rs.next()){
				jackVisited = true;
				success = true;
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(success);
		}
		return jackVisited;
	}

  public int getTurn() throws Exception{
	boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT MAX(turn) as current_turn FROM Jack_Moves";
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
		int turn = rs.getInt("current_turn");
		success = true;

		return turn;
	  }

	} finally {
	  db.closeConnection(success);
	}
	return -1; //should never get here
  }

  public int getCurrentJackLocation() throws Exception {
	boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT MAX(turn) as turn, destination_node FROM Jack_Moves";
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
		int dest_node = rs.getInt("destination_node");
		success = true;

		return dest_node;
	  }

	} finally {
	  db.closeConnection(success);
	}
	return -1; //should never get here
  }

  public Boolean insertJackMove(int turn, int dest_node, String move_type){
    boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Jack_Moves VALUES( ?,?,?)");
	  ps.setInt(1, turn);
	  ps.setString(2, move_type);
	  ps.setInt(3, dest_node);

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

  public Boolean insertJackMove(Jack_Move move){
    return insertJackMove(move.getTurn(), move.getDest_node(), move.getMove_type().toString());
  }

  public Boolean clear(){
	boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  PreparedStatement ps = connection.prepareStatement("DELETE FROM Jack_Moves");

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
