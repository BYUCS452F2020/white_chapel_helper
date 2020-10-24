package DAOs;

import Models.Connection;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionDAOTest {

  @org.junit.jupiter.api.Test
  void insertAndGetConnection() {
    ConnectionDAO dao = new ConnectionDAO();
    Boolean success = dao.insertConnection(1,2,"street");
    Connection conn = dao.getConnection(1,2);
    System.out.println(success);
    System.out.println(conn.toString());
    Assertions.assertEquals(true, success);
    Assertions.assertEquals(1, conn.getFrom_node());
    Assertions.assertEquals(2, conn.getTo_node());
  }
}