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
    dao.setJackVisitedTrue(2);
    Assertions.assertTrue(dao.checkForClue(2, 2));
  }

  @Test
  void isValidMove() {
    Assertions.assertTrue(dao.isValidNode(3));
    Assertions.assertFalse(dao.isValidNode(0));
  }

  @Test
  void getConnections() {
    //System.out.println("beta");
  }
}
