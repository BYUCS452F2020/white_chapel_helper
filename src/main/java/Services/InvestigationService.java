package Services;
import DAOs.Database;
import DAOs.JackMoveDAO;
import DAOs.InvestigationDAO;

public class InvestigationService {

  // this function investigates a node
  // return if Jack has been to that node
  // update DB with investigation data

  public Boolean investigate(int node, int turn){
    boolean jackVisited = false;
    JackMoveDAO jmd = new JackMoveDAO();
    InvestigationDAO invd = new InvestigationDAO();

    jackVisited = jmd.jackWasHere(node);
    invd.insertInvestigation(node, turn, jackVisited);

    return jackVisited;
  }
}
