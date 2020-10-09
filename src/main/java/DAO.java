
public class DAO {
  //NOTE this function accesses local SQLite DB, we may change that later

  // git clone ______
  // git commit (updates local code)
  // git push (makes all local changes --> public changes)
  // git pull (get all public changes any else has done)
  // git branch (see all branch names)
  // git branch NEW_BRANCH_NAME (make a new branch called NEW_BRANCH_NAME)
  // git checkout NEW_BRANCH_NAME (switch to the branch called NEW_BRANCH_NAME)

  //TODO (func) get all possible node (ie. any node Jack could have been, ever, whole Possible_Locations table)
  //TODO return whole Connections table
  //TODO return whole Investigations table
  //TODO return whole Jack_Moves table

  //TODO (func) get possible nodes given turn num

  //TODO (func) get all connecting nodes, given base node and turn type (road/alley)
  // Select to_node from CONN. where from_node = given and connection_type=given

  //TODO (func) increment turn, and add all new possible nodes to DB
  	// get all nodes for previous turn
  	// for each node
  		// get all possible NEW locations, starting from this node
  	// update DB with all possible new locations

  //TODO (func) create DB locally, and fill it
  	// @Joanna is working on this on the DB branch

  //TODO (func) return DB connection
  	// @Nathan is owkring on this in the conn branch

  //TODO (func) investigate a node, return if it has a clue and update DB

  //TODO (func) have Jack move (moves randomly)

  //TODO (func) have Jack move (moves based on user input)

  //TODO (helper func) isValidJackMove (checks if nodes are connected)

  //TODO (other file) .csv file representing simple map

  //TODO (func) returns how many special moves Jack has used

  //TODO (func) seed Database with designated starting node

  //TODO (func) calculate shortest DISTANCE between nodes @Adam

  //TODO (func) return shortest PATH between 2 nodes

}
