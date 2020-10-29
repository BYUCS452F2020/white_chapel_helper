package Services;

import DAOs.ConnectionDAO;
import DAOs.JackMoveDAO;
import DAOs.PossibleLocationDAO;
import Models.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PossibleLocationServiceTest {

    JackMoveService jack_moves = new JackMoveService();
    PossibleLocationService locations_service = new PossibleLocationService();

    Connection conn1, conn2, conn3, conn4, conn5, conn6, conn7, conn8, conn9, conn10;
    ConnectionDAO dao = new ConnectionDAO();
    PossibleLocationDAO locations_dao = new PossibleLocationDAO();
    JackMoveDAO jack_dao = new JackMoveDAO();

    //ConnectionService service =  new ConnectionService();

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

    @Test
    void test_updated_locations1() throws Exception {
        jack_dao.clear();
        locations_dao.clear();
        jack_moves.setJackStartNode(1);
        locations_service.jackMovedUpdateTable(1, "street");
        locations_service.jackMovedUpdateTable(2, "street");
        locations_service.jackMovedUpdateTable(3, "street");
        String test = locations_dao.getAllPossLoc().toString();
        System.out.println(test);

        Assertions.assertNotNull(locations_dao.getPossibleLoc(2, 1));
        Assertions.assertNotNull(locations_dao.getPossibleLoc(1, 3));
        Assertions.assertNotNull(locations_dao.getPossibleLoc(3, 5));
    }

    @Test
    void test_updated_locationes_alley() throws Exception {
        jack_dao.clear();
        locations_dao.clear();
        jack_moves.setJackStartNode(1);
        locations_service.jackMovedUpdateTable(1, "alley");

        Assertions.assertNotNull(locations_dao.getPossibleLoc(1, 5));
        Assertions.assertNull(locations_dao.getPossibleLoc(1, 3));
    }


}
