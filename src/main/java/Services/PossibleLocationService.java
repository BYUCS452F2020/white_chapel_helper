package Services;

import Models.Investigation;

public class PossibleLocationService {

  // Jack has moved, update the PossibleLocationTable to include all possible nodes for this new turn
  public void jackMovedUpdateTable(){
    //TODO
	// get all nodes for previous turn
	// for each node
	// get all possible NEW locations, starting from this node
	// update DB with all possible new locations
  }

  // a player has investigated a node, update the PossibleLocations table
  public void investigatedUpdateTable(Investigation investigation){
    //TODO
  }
}
