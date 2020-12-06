package DAOs_Graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Models.Connection;
import org.junit.jupiter.api.*;
import DAOs_Graph.LocationDAO;
import DAOs_Graph.Database_Graph;


public class LocationDAOTest {

  private LocationDAO dao = new LocationDAO();

  @Test
  void setJackVisited() {
    dao.setJackVisitedTrue(99);
  }

  @Test
  void isValidMove() {
    Assertions.assertTrue(dao.isValidNode(99));
    Assertions.assertFalse(dao.isValidNode(0));
  }

  @AfterEach
  public void cleanUp() {
    dao.deleteLocations();
  }

  @Test
  void addConnections() {

  }

  @Test
  void addNodes() {
    dao.addNode(1);
    dao.addNode(2);
    dao.addNode(3);
    dao.addNode(5);

    dao.addStreetConnection(1, 3);
    dao.addStreetConnection(1, 2);
    dao.addStreetConnection(2, 5);

    System.out.println(dao.getAllConnections(1).elementAt(0));
  }

  @Test
  void getConnections() {
    System.out.println("beta");
  }
}
