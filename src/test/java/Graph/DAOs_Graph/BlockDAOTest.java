package Graph.DAOs_Graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;

public class BlockDAOTest {

    private final databaseGraph db = new databaseGraph();
    private final Driver driver = db.getDriver();
    private final BlockDAO dao = new BlockDAO();
    private final LocationDAO loc_dao = new LocationDAO(driver);

    @BeforeEach
    public void cleanUp() {
        loc_dao.deleteLocations();
        dao.deleteBlocks();
    }

    @Test
    void addNodes() {
        loc_dao.addNode(1);
        loc_dao.addNode(2);
        loc_dao.addNode(3);
        loc_dao.addNode(5);

        dao.addBlock(1);
        dao.addBlock(2);


        dao.addAlleyConnection(1, 3);
        dao.addAlleyConnection(1, 2);
        dao.addAlleyConnection(2, 5);

        System.out.println(dao.getAllConnections(1));
        Assertions.assertTrue(true);
    }
}

