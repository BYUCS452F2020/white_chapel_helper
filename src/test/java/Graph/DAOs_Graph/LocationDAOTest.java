package Graph.DAOs_Graph;

import Graph.GraphServices.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.neo4j.driver.Driver;

import java.util.List;


public class LocationDAOTest {
  private final databaseGraph db = new databaseGraph();
  private final Driver driver = db.getDriver();
  private final LocationDAO dao = new LocationDAO(driver);

  @BeforeEach
  void setup() throws Exception {
    db.clear_graph();
    BoardService service = new BoardService(driver);
    service.loadDummyDataModerate();
  }

  @Test
  void testSetJackVisited() {
    dao.setJackVisitedTrue(100);
    Assertions.assertTrue(dao.checkForClue(100, 2));
  }

  @Test
  void testIsValidNode() {
    Assertions.assertTrue(dao.isValidNode(100));
    Assertions.assertFalse(dao.isValidNode(0));
  }

  @Test
  void testIsValidMove(){
    Assertions.assertFalse(dao.isValidMove(100, 89));
    Assertions.assertTrue(dao.isValidMove(100, 85));
  }

  @Test
  void getAllLocByTurn() {
    List<Integer> possLoc= dao.getAllLocByTurn(100, 1);
    Assertions.assertEquals(8, possLoc.size()); // I know that 100 is connected to 8 nodes
  }
}
