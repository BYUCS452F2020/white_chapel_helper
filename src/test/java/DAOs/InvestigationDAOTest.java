package DAOs;

import Models.Investigation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

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
}