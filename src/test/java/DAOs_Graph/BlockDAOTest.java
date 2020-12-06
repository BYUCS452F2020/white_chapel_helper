package DAOs_Graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlockDAOTest {

    private BlockDAO dao = new BlockDAO();
    private LocationDAO loc_dao = new LocationDAO();

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
    }
}

