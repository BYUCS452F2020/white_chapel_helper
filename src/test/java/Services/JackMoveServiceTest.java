package Services;

import DAOs.JackMoveDAO;
import DAOs.PossibleLocationDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JackMoveServiceTest {

    JackMoveService jack_move_service = new JackMoveService();
    JackMoveDAO jack_move_DAO = new JackMoveDAO();
    PossibleLocationDAO locations_dao = new PossibleLocationDAO();



    @Test
    void test_start_node(){
        locations_dao.clear();
        jack_move_DAO.clear();
        jack_move_service.setJackStartNode(3);

        Assertions.assertNotNull(jack_move_DAO.getJackMove(0));
        Assertions.assertEquals(3, jack_move_DAO.getJackMove(0).getDest_node());

        Assertions.assertNotNull(locations_dao.getPossLocFromTurn(0));
        Assertions.assertEquals(3, locations_dao.getPossLocFromTurn(0).get(0).getNode());
    }
}
