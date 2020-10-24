package DAOs;

import Models.Investigation;
import org.junit.jupiter.api.Assertions;

class InvestigationDAOTest {

  @org.junit.jupiter.api.Test
  void insertAndGetConnection() {
    InvestigationDAO dao = new InvestigationDAO();
    Boolean success = dao.insertInvestigation(1,2,true);
    Investigation investigation = dao.getInvestigation(1,2);
    System.out.println(success);
    System.out.println(investigation.toString());
    Assertions.assertEquals(true, success);
    Assertions.assertEquals(1, investigation.getNode());
    Assertions.assertEquals(2, investigation.getTurn_investigated());
  }
}