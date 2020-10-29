package DAOs;

import Models.Investigation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvestigationDAOTest {

  InvestigationDAO dao;
  @BeforeEach
  void setup(){
    dao = new InvestigationDAO();
    dao.clear();
  }
  @org.junit.jupiter.api.Test
  void insertAndGetConnection() {
    Boolean success = dao.insertInvestigation(1,2,true);
    Investigation investigation = dao.getInvestigation(1,2);
    System.out.println(success);
    System.out.println(investigation.toString());
    Assertions.assertEquals(true, success);
    Assertions.assertEquals(1, investigation.getNode());
    Assertions.assertEquals(2, investigation.getTurn_investigated());
  }

  @Test
  void checkForLaterClues() {
    loadTable();
    boolean false11 = dao.couldHaveVisited(1,2);
    boolean true12 = dao.couldHaveVisited(1, 5);
    boolean true13 = dao.couldHaveVisited(1, 6);
    boolean false21 = dao.couldHaveVisited(2, 2);
    boolean false22 = dao.couldHaveVisited(2,7);
    boolean true23 = dao.couldHaveVisited(2,9);
    boolean true31 = dao.couldHaveVisited(3, 1);
    boolean true32 = dao.couldHaveVisited(3, 3);
    boolean true33 = dao.couldHaveVisited(3, 8);

    Assertions.assertEquals(false, false11);
    Assertions.assertEquals(true, true12);
    Assertions.assertEquals(true, true13);
    Assertions.assertEquals(false, false21);
    Assertions.assertEquals(false, false22);
    Assertions.assertEquals(true, true23);
    Assertions.assertEquals(true, true31);
    Assertions.assertEquals(true, true32);
    Assertions.assertEquals(true, true33);
  }

  void loadTable(){
    dao.insertInvestigation(1, 3, false);
    dao.insertInvestigation(1, 6, true);
    dao.insertInvestigation(2, 5, false);
    dao.insertInvestigation(2, 8, false);
    dao.insertInvestigation(3, 2, true);
    dao.insertInvestigation(3, 7, true);
  }
}