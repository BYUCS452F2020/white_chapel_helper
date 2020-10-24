package DAOs;

import Models.Jack_Move;
import org.junit.jupiter.api.Assertions;

class JackMoveDAOTest {

  @org.junit.jupiter.api.Test
  void insertAndGetJackMove() throws Exception {
    JackMoveDAO dao = new JackMoveDAO();
    Jack_Move jack_move = new Jack_Move(10, 20, "carriage");
    Boolean success = dao.insertJackMove(jack_move);
    Jack_Move extracted_move = dao.getJackMove(jack_move.getTurn());
    System.out.println(success);
    System.out.println(extracted_move.toString());
    Assertions.assertEquals(true, success);
    Assertions.assertEquals(10, jack_move.getTurn());
    Assertions.assertEquals(20, jack_move.getDest_node());
    Assertions.assertEquals("carriage", jack_move.getMove_type().toString());
  }
}