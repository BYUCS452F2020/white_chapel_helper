package DAOs;

import Models.Jack_Move;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JackMoveDAO {
  private Database db = new Database();

  public Jack_Move getJackMove(int turn){
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Jack_Moves WHERE turn= " + turn;
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
	    String move_type = rs.getString("move_type");
	    int dest_node = rs.getInt("destination_node");

		return new Jack_Move(turn, dest_node, move_type);
	  }

	} catch(Exception e) {
	  e.printStackTrace();
	}
	return null;
  }

  public Boolean insertJackMove(int turn, int dest_node, String move_type){
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Jack_Moves VALUES( ?,?,?)");
	  ps.setInt(1, turn);
	  ps.setString(2, move_type);
	  ps.setInt(3, dest_node);

	  int i = ps.executeUpdate();
	  if( i==1){
	    return true;
	  }

	} catch(DataAccessException | SQLException e) {
	  e.printStackTrace();
	}
	return false;
  }

  public Boolean insertJackMove(Jack_Move move){
    return insertJackMove(move.getTurn(), move.getDest_node(), move.getMove_type().toString());
  }
}
