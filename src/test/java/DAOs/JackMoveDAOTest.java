package DAOs;

import Models.Jack_Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class JackMoveDAOTest {
  private JackMoveDAO dao;
  private Jack_Move jack_move;

  @BeforeEach
  void setup()throws Exception{
    dao = new JackMoveDAO();
    jack_move = new Jack_Move(10, 20, "carriage");
    dao.clear();
  }

  @org.junit.jupiter.api.Test
  void insertAndGetJackMove(){
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