package DAOs;

import Models.Connection;
import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDAO {
  private final Database db = new Database();
  public ConnectionDAO(){}

  public Connection getConnection(int from, int to){
    boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Connections WHERE from_node= " + from + " and to_node = " + to;
	  ResultSet rs = stmt.executeQuery(query);

	  if(rs.next()){
	    int from_node = rs.getInt("from_node");
	    int to_node   = rs.getInt("to_node");
	    String conn_type = rs.getString("connection_type");
		success = true;

		return new Connection(from_node, to_node, conn_type);
	  }
	} catch(Exception e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
	return null;
  }

  //NOTE currently this function insert to-->from AND from --> to
  // basically, the if you can go from A to B, you should be able to go from B to A
  public Boolean insertConnection(int from, int to, String conn_type){
	boolean success = false;
    try {
	  java.sql.Connection connection = db.getConnection();
	  PreparedStatement ps = connection.prepareStatement("INSERT INTO Connections VALUES( ?,?,?)");
	  ps.setInt(1, from);
	  ps.setInt(2, to);
	  ps.setString(3, conn_type);

	  int i1 = ps.executeUpdate();

	  ps = connection.prepareStatement("INSERT INTO Connections VALUES( ?,?,?)");
	  ps.setInt(1, to); //NOTE notice the order of from/to is opposite from above
	  ps.setInt(2, from);
	  ps.setString(3, conn_type);

	  int i2 = ps.executeUpdate();

	  if( i1==1 & i2 ==1){
	    success = true;
	    return success;
	  }

	} catch(DataAccessException e) {
	  e.printStackTrace();
	  success = false;
	} catch(SQLException e){
      if(e.getMessage().contains("Abort due to constraint violation (UNIQUE constraint failed:")){
        success = true;
        return success;
	  }
	}finally{
      db.closeConnection(success);
	}
	return success;
  }

  public Boolean insertConnection(Connection conn){
    return insertConnection(conn.getFrom_node(), conn.getTo_node(), conn.getConnectionType().toString());
  }

  public List<Connection> getConnectionsFromNode(int node){
	List<Connection> connecting_nodes = new ArrayList<>();
	boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Connections WHERE from_node = " + node;
	  ResultSet rs = stmt.executeQuery(query);
	  compileReturnList(connecting_nodes, rs);
	  success = true;
	} catch(Exception e) {
	  e.printStackTrace();
	} finally {
	  db.closeConnection(success);
	}
	return connecting_nodes;
  }

  public List<Connection> getConnectionsFromNodeAndTurnType(int node, String move_type){
	List<Connection> connecting_nodes = new ArrayList<>();
	boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Connections WHERE from_node = " + node +
			  " AND connection_type = '" + move_type + "'";
	  ResultSet rs = stmt.executeQuery(query);

	  compileReturnList(connecting_nodes, rs);
	  success = true;
	} catch(Exception e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
	return connecting_nodes;
  }

  public List<Connection> getMap(){
	List<Connection> map = new ArrayList<>();
	boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  Statement stmt = connection.createStatement();
	  String query = "SELECT * FROM Connections";
	  ResultSet rs = stmt.executeQuery(query);
	  compileReturnList(map, rs);
	  success = true;
	} catch(Exception e) {
	  e.printStackTrace();
	}finally{
	  db.closeConnection(success);
	}
	return map;
  }

  private void compileReturnList(List<Connection> map, ResultSet rs) throws Exception {
	while(rs.next()){
	  int from_temp = rs.getInt("from_node");
	  int to_temp = rs.getInt("to_node");
	  String conn_type = rs.getString("connection_type");
	  map.add(new Connection(from_temp, to_temp, conn_type));
	}
  }

  public void clear(){
    boolean success = false;
	try {
	  java.sql.Connection connection = db.getConnection();
	  PreparedStatement ps = connection.prepareStatement("DELETE FROM Connections");

	  int i1 = ps.executeUpdate();
	  success = true;
	} catch(DataAccessException | SQLException e) {
	  e.printStackTrace();
	}finally {
	  db.closeConnection(success);
	}
  }
}
