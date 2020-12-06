package DAOs_Graph;

import DAOs.Database;
import org.neo4j.driver.*;

public class LocationDAO {
    private Database_Graph ds = new Database_Graph();
    private Driver driver = ds.getDriver();

    // this creates a location Node. these are the Nodes found on the board each with a given number.
    // the number of the location is inputted as part of this function. the Location Node has values for:
    // Number, the number the Node is
    // Jack_visited, whether or not Jack has been to this space (for computers view only)
    // Turn_investigated, the most recent turn in which Node has been investigated (default is 0)
    // Clue_found, if a clue was found at given Node on most recent investigation
    public void addNode(int number){

        try (Session session = driver.session()){


            String dataString = "CREATE (:Location {Number:" + number + ", Jack_visited:\"No\"," +
                    " Turn_investigated:0, Clue_found:\"No\"})";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.toString();
                }
            });
        }
    }

    public void addStreetConnection(int node1, int node2){
        try (Session session = driver.session()) {
            String dataString = "MATCH (a:Location), (b:Location) " +
                    "WHERE a.Number = " + node1 + " AND b.Number = " + node2 + " CREATE (a)-[:STREET]-> (b)";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.toString();
                }
            });
        }
    }

    public void deleteLocations(){
        try (Session session = driver.session()){

            String dataString = "MATCH (n:Location) DETACH DELETE n";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.toString();
                }
            });
        }
    }
    
    public Vector<Integer> getAllConnections(int number){
        Vector<Integer> return_vector = new Vector<>();
        try (Session session = driver.session()){

            String dataString = "MATCH (n {Number:" + number + "})-[:STREET]->(results)  RETURN results.Number";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);

                    while (result.hasNext()){
                        return_vector.add(result.next().get(0).asInt());
                        //result.next();
                    }
                    return null;
                }
            });
        }
        return return_vector;
    }
}

