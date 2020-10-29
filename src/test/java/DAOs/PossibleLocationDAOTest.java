package DAOs;

import Models.Connection;
import Models.Possible_Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PossibleLocationDAOTest {
  Possible_Location loc1 = new Possible_Location(1,1);
  Possible_Location loc2 = new Possible_Location(1,2);
  Possible_Location loc3 = new Possible_Location(1,3);

  Possible_Location loc4 = new Possible_Location(2,3);
  Possible_Location loc5 = new Possible_Location(2,4);
  Possible_Location loc6 = new Possible_Location(2,5);

  PossibleLocationDAO dao;

  @BeforeEach
  void setup(){
    dao = new PossibleLocationDAO();
    dao.clear();
  }

  @org.junit.jupiter.api.Test
  void insertAndGetConnection() {
    Boolean success = dao.insertPossibleLoc(1,2);
    Possible_Location possibleLoc = dao.getPossibleLoc(1, 2);
    System.out.println(success);
    System.out.println(possibleLoc.toString());
    Assertions.assertEquals(true, success);
    Assertions.assertEquals(1, possibleLoc.getTurn());
    Assertions.assertEquals(2, possibleLoc.getNode());
  }

  @Test
  void getAllLoc(){
    loadTable();
    List<Possible_Location> allPossLoc = dao.getAllPossLoc();
    List<Possible_Location> expected = new ArrayList<Possible_Location>(){{add(loc1);
    add(loc2);
    add(loc3);
    add(loc4);
    add(loc5);
    add(loc6);
    }};
    Assertions.assertEquals(expected, allPossLoc);
  }

  @Test
  void getMinDistance(){
    loadTable();
    int node1min = dao.getSoonestReached(1);
    int node3min = dao.getSoonestReached(3);
    int node5min = dao.getSoonestReached(5);
    int nodeFailed = dao.getSoonestReached(8);
    Assertions.assertEquals(1, node1min);
    Assertions.assertEquals(1, node3min);
    Assertions.assertEquals(2, node5min);
    Assertions.assertEquals(-1, nodeFailed);
  }

  @Test
  void getLocFromTurn(){
    loadTable();
    List<Possible_Location> possLoc = dao.getPossLocFromTurn(2);
    List<Possible_Location> expected = new ArrayList<Possible_Location>(){{
      add(loc4);
      add(loc5);
      add(loc6);
    }};
    Assertions.assertEquals(expected, possLoc);
  }

  void loadTable(){
    dao.insertPossibleLoc(loc1);
    dao.insertPossibleLoc(loc2);
    dao.insertPossibleLoc(loc3);
    dao.insertPossibleLoc(loc4);
    dao.insertPossibleLoc(loc5);
    dao.insertPossibleLoc(loc6);
  }
}