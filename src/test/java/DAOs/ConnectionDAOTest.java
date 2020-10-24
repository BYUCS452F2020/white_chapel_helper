package DAOs;

import Models.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionDAOTest {
  Connection conn1, conn2, conn3, conn4, conn5, conn6, conn7, conn8, conn9, conn10;
  ConnectionDAO dao = new ConnectionDAO();

  @BeforeEach
  void load() throws Exception{
    dao.clear();
    conn1 = new Connection(1,2,"street");
    conn2 = new Connection(1,3,"street");
    conn3 = new Connection(1,4,"street");
    conn4 = new Connection(2,3,"street");
    conn5 = new Connection(2,4,"street");
    conn6 = new Connection(2,5,"street");
    conn7 = new Connection(1,5,"alley");
    conn8 = new Connection(3,5,"alley");
    conn9 = new Connection(3,6,"street");
    conn10 = new Connection(1,6,"alley");

    dao.insertConnection(conn1);
    dao.insertConnection(conn2);
    dao.insertConnection(conn3);
    dao.insertConnection(conn4);
    dao.insertConnection(conn5);
    dao.insertConnection(conn6);
    dao.insertConnection(conn7);
    dao.insertConnection(conn8);
    dao.insertConnection(conn9);
    dao.insertConnection(conn10);
  }

  @org.junit.jupiter.api.Test
  void insertAndGetConnection() {
    Boolean success = dao.insertConnection(10,2,"street");
    Connection conn = dao.getConnection(10,2);
    System.out.println(success);
    System.out.println(conn.toString());
    Assertions.assertEquals(true, success);
    Assertions.assertEquals(10, conn.getFrom_node());
    Assertions.assertEquals(2, conn.getTo_node());
  }

  @Test
  void getMap(){
    List<Connection> map = dao.getMap();
    Assertions.assertEquals(20, map.toArray().length);
    // 20 because we inserted 10 connections, and each was duplicated to allow movement bi-directionally
  }

  @Test
  void getConnectionsFromNode(){
    List<Connection> from_one = dao.getConnectionsFromNode(1);
    List<Connection> from_five = dao.getConnectionsFromNode(5);

    Assertions.assertEquals(5, from_one.toArray().length);
    Assertions.assertEquals(3, from_five.toArray().length);
  }

  @Test
  void getConnectionsFromNodeAndTurnType(){
    List<Connection> from_one = dao.getConnectionsFromNodeAndTurnType(1, "street");
    List<Connection> from_five = dao.getConnectionsFromNodeAndTurnType(5, "alley");

    Assertions.assertEquals(3, from_one.toArray().length);
    Assertions.assertEquals(2, from_five.toArray().length);
  }
}