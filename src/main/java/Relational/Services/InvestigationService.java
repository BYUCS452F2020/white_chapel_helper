package Relational.Services;
import Relational.DAOs.JackMoveDAO;
import Relational.DAOs.InvestigationDAO;

public class InvestigationService {

  // this function investigates a node
  // return if Jack has been to that node
  // update DB with investigation data

  JackMoveDAO moveDAO = new JackMoveDAO();
  InvestigationDAO investigationDAO = new InvestigationDAO();
  
  public Boolean investigate(int node, int turn){
    boolean jackVisited = false;

    jackVisited = moveDAO.jackWasHere(node);
    investigationDAO.insertInvestigation(node, turn, jackVisited);

    return jackVisited;
  }
}
