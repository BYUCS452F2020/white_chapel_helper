package DAOs;

import Models.Connection;
import java.sql.*;

public class ConnectionDAO {
  private Database db = new Database();
  public ConnectionDAO(){}

  public Connection getConnection(int from, int to){
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Connections WHERE from_node= " + from + " and to_node = " + to;
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
	    int from_node = rs.getInt("from_node");
	    int to_node   = rs.getInt("to_node");
	    String conn_type = rs.getString("connection_type");

	    Connection conn = new Connection(from_node, to_node, conn_type);
	    return conn;
	  }

	} catch(Exception e) {
	  e.printStackTrace();
	}
	return null;
  }

  public Boolean insertConnection(int from, int to, String conn_type){
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Connections VALUES( ?,?,?)");
	  ps.setInt(1, from);
	  ps.setInt(2, to);
	  ps.setString(3, conn_type);

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
