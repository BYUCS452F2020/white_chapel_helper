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
	}
	return null;
  }

  public Boolean insertInvestigation(int node, int turn_investigated, Boolean hasClue){
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Investigations VALUES( ?,?,?)");
	  ps.setInt(1, node);
	  ps.setInt(2, turn_investigated);
	  ps.setBoolean(3, hasClue);

	  int i = ps.executeUpdate();
	  if( i==1){
	    return true;
	  }

	} catch(DataAccessException | SQLException e) {
	  e.printStackTrace();
	}
	return false;
  }
}
