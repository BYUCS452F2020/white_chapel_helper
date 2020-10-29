package DAOs;

import Models.Investigation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InvestigationDAO {
  private Database db = new Database();
  public InvestigationDAO(){}

  public Investigation getInvestigation(int node, int turnInvestigated){
    Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Investigations WHERE node= " + node + " and turn_investigated = " + turnInvestigated;
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
	    Boolean hasClue = rs.getBoolean("has_clue");

		return new Investigation(node, turnInvestigated, hasClue);
	  }

	} catch(Exception e) {
	  e.printStackTrace();
	} finally {
	  db.closeConnection(success);
	}
	return null;
  }

  public Boolean insertInvestigation(int node, int turn_investigated, Boolean hasClue){
    Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Investigations VALUES( ?,?,?)");
	  ps.setInt(1, node);
	  ps.setInt(2, turn_investigated);
	  ps.setBoolean(3, hasClue);

	  int i = ps.executeUpdate();
	  if( i==1){
	    success = true;
	    return success;
	  }

	} catch(DataAccessException | SQLException e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
	return success;
  }

  public Boolean couldHaveVisited(int node, int turn) {
  	// Determine whether a node has been found without a clue on a later turn.
	  boolean success = false;
	  try {
		  java.sql.Connection connection = db.getConnection();
		  Statement stmt = connection.createStatement();
		  String query = "SELECT * FROM Investigations WHERE node= " + node +
				         " and turn_investigated > " + turn +
				  		 " and has_clue = 0";
		  ResultSet rs = stmt.executeQuery(query);

		  if(rs.next()){
			  return false; // If there is a result, then the node had no clue on a later turn
		  }
		  success = true;

	  } catch(Exception e) {
		  e.printStackTrace();
	  } finally {
		  db.closeConnection(success);
	  }
  	return true;
  }

  public Boolean clear(){
	Boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  PreparedStatement ps = connection.prepareStatement("DELETE FROM Investigations");

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
