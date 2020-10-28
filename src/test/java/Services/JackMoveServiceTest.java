package Services;

import DAOs.JackMoveDAO;
import DAOs.PossibleLocationDAO;
import Models.Connection;
import Models.Possible_Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JackMoveServiceTest {

    JackMoveService jack_move_service = new JackMoveService();
    JackMoveDAO jack_move_DAO = new JackMoveDAO();
    PossibleLocationDAO locations_dao = new PossibleLocationDAO();
    BoardService boardService = new BoardService();

    @BeforeEach
    void setUp() {
        locations_dao.clear();
        jack_move_DAO.clear();
    }

    @Test
    void test_start_node(){
        jack_move_service.setJackStartNode(3);

        Assertions.assertNotNull(jack_move_DAO.getJackMove(0));
        Assertions.assertEquals(3, jack_move_DAO.getJackMove(0).getDest_node());

        Assertions.assertNotNull(locations_dao.getPossLocFromTurn(0));
        Assertions.assertEquals(3, locations_dao.getPossLocFromTurn(0).get(0).getNode());
    }

    @Test
    void test_JackMoves()throws Exception{
        // TODO introduce mocks
        boardService.loadDummyDataModerate();
        jack_move_service.setJackStartNode(100);

        Assertions.assertThrows(Exception.class, () -> {
            jack_move_service.jackMoves(1, "street");
        });

        jack_move_service.jackMoves(125, "street");
        List<Possible_Location> poss_loc = locations_dao.getAllPossLoc();
        Assertions.assertTrue(poss_loc.contains(new Possible_Location(1, 85)));
        Assertions.assertEquals(9, poss_loc.size());
        // 1 turn 0, 8 turn 1

        jack_move_service.jackMoves(126, "street");
        poss_loc = locations_dao.getAllPossLoc();
        Assertions.assertTrue(poss_loc.contains(new Possible_Location(2, 101)));
        Assertions.assertEquals(25, poss_loc.size());

    }
}
