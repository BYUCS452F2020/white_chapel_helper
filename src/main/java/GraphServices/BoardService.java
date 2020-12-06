package GraphServices;

import DAOs.ConnectionDAO;
import DAOs.DataAccessException;
import DAOs.Database;
import DAOs_Graph.LocationDAO;
import Models.Connection;

import java.util.Arrays;
import java.util.List;

public class BoardService {

  //loads very simple map into DB, ~10 nodes
  public void loadDummyDataSimple(){
    //TODO
  }

  //loads medium-sized map into DB, 1 neighborhood of game board
  public void loadDummyDataModerate() throws Exception {
//    clearDB();
    LocationDAO dao = new LocationDAO();
    List<Connection> map = Arrays.asList(
            new Connection(120,99, "STREET"),
            new Connection(120,100, "STREET"),
            new Connection(120,122, "STREET"),
            new Connection(120,123, "STREET"),

            new Connection(99,100, "STREET"),
            new Connection(99,86, "STREET"),
            new Connection(99,85, "STREET"),

            new Connection(100,86, "STREET"),
            new Connection(100,85, "STREET"),
            new Connection(100,122, "STREET"),
            new Connection(100,123, "STREET"),
            new Connection(100,124, "STREET"),
            new Connection(100,125, "STREET"),

            new Connection(122,123, "STREET"),
            new Connection(122,124, "STREET"),
            new Connection(122,125, "STREET"),

            new Connection(123,124, "STREET"),
            new Connection(123,125, "STREET"),

            new Connection(86,85, "STREET"),
            new Connection(86,56, "STREET"),
            new Connection(86,69, "STREET"),
            new Connection(86,102, "STREET"),

            new Connection(85,124, "STREET"),
            new Connection(85,101, "STREET"),
            new Connection(85,126, "STREET"),

            new Connection(124,125, "STREET"),
            new Connection(124,126, "STREET"),
            new Connection(124,101, "STREET"),

            new Connection(125,102, "STREET"),
            new Connection(125,126, "STREET"),
            new Connection(125,101, "STREET"),
            new Connection(125,143, "STREET"),
            new Connection(125,127, "STREET"),
            new Connection(124,102, "STREET"),

            new Connection(126,101, "STREET"),
            new Connection(126,143, "STREET"),
            new Connection(126,127, "STREET"),

            new Connection(101,143, "STREET"),
            new Connection(101,102, "STREET"),
            new Connection(101,127, "STREET"),

            new Connection(102,69, "STREET"),
            new Connection(102,127, "STREET"),
            new Connection(102,143, "STREET"),
            new Connection(102,56, "STREET"),

            new Connection(143,144, "STREET"),
            new Connection(143,128, "STREET"),
            new Connection(143,127, "STREET"),

            new Connection(127,128, "STREET"),
            new Connection(127,103, "STREET"),
            new Connection(127,69, "STREET"),

            new Connection(69,56, "STREET"),
            new Connection(69,70, "STREET"),
            new Connection(69,103, "STREET"),

            new Connection(103,70, "STREET"),
            new Connection(103,128, "STREET"),

            new Connection(56,57, "STREET"),

            new Connection(128,144, "STREET"),

            new Connection(144,129, "STREET"),
            new Connection(144,145, "STREET"),

            new Connection(70,87, "STREET"),
            new Connection(70,71, "STREET"),

            new Connection(87,71, "STREET"),
            new Connection(87,104, "STREET"),
            new Connection(87,129, "STREET"),

            new Connection(129, 145, "STREET"),
            new Connection(129,104, "STREET"),

            new Connection(71,104, "STREET"),
            new Connection(71,72, "STREET"),
            new Connection(71,88, "STREET"),

            new Connection(104,145, "STREET"),
            new Connection(104,88, "STREET"),
            new Connection(104,105, "STREET"),
            new Connection(104,130, "STREET"),

            new Connection(106,91, "STREET"),
            new Connection(106,107, "STREET"),
            new Connection(106,108, "STREET"),
            new Connection(106,131, "STREET"),

            new Connection(145,130, "STREET"),
            new Connection(145,131, "STREET"),
            new Connection(145,146, "STREET"),

            new Connection(130,88, "STREET"),
            new Connection(130,105, "STREET"),
            new Connection(130,131, "STREET"),
            new Connection(130,146, "STREET"),

            new Connection(88,72, "STREET"),
            new Connection(88,105, "STREET"),

            new Connection(89,73, "STREET"),
            new Connection(89,74, "STREET"),
            new Connection(89,90, "STREET"),
            new Connection(89,91, "STREET"),
            new Connection(89,107, "STREET"),
            new Connection(89,106, "STREET"),

            new Connection(72,73, "STREET"),
            new Connection(72,74, "STREET"),
            new Connection(72,90, "STREET"),
            new Connection(72,89, "STREET"),

            new Connection(105,89, "STREET"),
            new Connection(105,91, "STREET"),
            new Connection(105,107, "STREET"),
            new Connection(105,106, "STREET"),

            new Connection(131,108, "STREET"),
            new Connection(131,132, "STREET"),
            new Connection(131,134, "STREET"),
            new Connection(131,133, "STREET"),
            new Connection(131,146, "STREET"),

            new Connection(146,133, "STREET"),
            new Connection(146,147, "STREET"),

            new Connection(133,147, "STREET"),
            new Connection(133,108, "STREET"),
            new Connection(133,132, "STREET"),

            new Connection(147,134, "STREET"),
            new Connection(147,111, "STREET"),

            new Connection(134,108, "STREET"),
            new Connection(134,132, "STREET"),
            new Connection(134,111, "STREET"),

            new Connection(108,107, "STREET"),
            new Connection(108,92, "STREET"),
            new Connection(108,109, "STREET"),
            new Connection(108,110, "STREET"),
            new Connection(108,132, "STREET"),

            new Connection(132,107, "STREET"),
            new Connection(132,92, "STREET"),
            new Connection(132,109, "STREET"),
            new Connection(132,110, "STREET"),

            new Connection(107,91, "STREET"),
            new Connection(107,92, "STREET"),
            new Connection(107,109, "STREET"),
            new Connection(107,110, "STREET"),

            new Connection(91,90, "STREET"),
            new Connection(91,75, "STREET"),
            new Connection(91,94, "STREET"),
            new Connection(91,93, "STREET"),
            new Connection(91,92, "STREET"),

            new Connection(92,90, "STREET"),
            new Connection(92,75, "STREET"),
            new Connection(92,93, "STREET"),
            new Connection(92,94, "STREET"),
            new Connection(92,109, "STREET"),
            new Connection(92,110, "STREET"),

            new Connection(90,73, "STREET"),
            new Connection(90,74, "STREET"),
            new Connection(90,75, "STREET"),
            new Connection(90,76, "STREET"),
            new Connection(90,77, "STREET"),
            new Connection(90,94, "STREET"),
            new Connection(90,93, "STREET"),

            new Connection(57,42, "STREET"),
            new Connection(57,58, "STREET"),
            new Connection(57,73, "STREET"),

            new Connection(73,42, "STREET"),
            new Connection(73,58, "STREET"),
            new Connection(73,76, "STREET"),
            new Connection(73,75, "STREET"),
            new Connection(73,74, "STREET"),

            new Connection(42,22, "STREET"),
            new Connection(42,58, "STREET"),

            new Connection(58, 76, "STREET"),
            new Connection(58,75, "STREET"),
            new Connection(58,74, "STREET"),

            new Connection(74,76, "STREET"),
            new Connection(74,75, "STREET"),

            new Connection(75,76, "STREET"),
            new Connection(75,77, "STREET"),
            new Connection(75,94, "STREET"),
            new Connection(75,93, "STREET"),

            new Connection(76,77, "STREET"),
            new Connection(76,94, "STREET"),

            new Connection(22,23, "STREET"),
            new Connection(22,77, "STREET"),

            new Connection(23,77, "STREET"),

            new Connection(77,94, "STREET"),

            new Connection(94,93, "STREET"),
            new Connection(94,109, "STREET"),
            new Connection(94,110, "STREET"),
            new Connection(94,111, "STREET"),

            new Connection(93,109, "STREET"),
            new Connection(93,110, "STREET"),
            new Connection(93,111, "STREET"),

            new Connection(109,110, "STREET"),
            new Connection(109,111, "STREET"),

            new Connection(110,111, "STREET"));
//    System.out.println("loading " + map.size() + " connections into DB");
    for(Connection temp: map){
      dao.addNode(temp.getFrom_node());
    }
    for(Connection temp: map){
      dao.addStreetConnection(temp.getFrom_node(), temp.getTo_node());
    }
  }

  //loads full white chapel map into DB
  public void loadData(){
    //TODO
  }

//  private void clearDB() throws DataAccessException {
//    Database db = new Database();
//    db.getConnection();
//    db.clearTables();
//    db.closeConnection(true);
//  }
}
