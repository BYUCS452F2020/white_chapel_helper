package Graph.DAOs_Graph;

import org.neo4j.driver.*;

import java.util.Vector;

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

            String dataString = "MATCH (n:Location {Number:" + number + "})-[:STREET]->(results)  RETURN results.Number";

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

    public void setJackVisitedTrue(int node){
        try (Session session = driver.session()) {
            String dataString = "MATCH (node:Location) " +
                    "WHERE node.Number = " + node +  " SET node.Jack_visited = \"Yes\"";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.toString();
                }
            });
        }
    }

    // this function should work (query works in browser)
    public boolean isValidNode(int node){
        boolean answer;
        try (Session session = driver.session()) {
            String dataString = "MATCH (node:Location) where node.Number = " + node + " return node";

            answer = session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.hasNext();
                }
            });
        }
        return answer;
    }

    // this works in browser, will test tomorrow in code (it's late and my connection is weird)
    public boolean isValidMove(int start, int destination) {
        try(Session session = driver.session()) {
            String dataString = "MATCH (n:Location {Number: " + start + " })-[:STREET]->" +
                    "(results:Location {Number: " + destination + " })  RETURN results.Number";
            // MATCH (n:Location {Number: 100})-[:STREET]->(results:Location {Number:125})  RETURN results.Number
            session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.hasNext();
                }
            });
        }
        return false;
    }

    public boolean checkForClue(int number, int turn){
        boolean answer;
        try (Session session = driver.session()){

            String dataString = "MATCH (n:Location) WHERE n.Number = " + number + " RETURN n.Jack_visited";

            answer = session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    if (result.single().get(0).toString().equals("\"Yes\"")){
                        return true;
                    }
                    return false;
                }
            });
        }
        if (answer){
            try (Session session = driver.session()){

                String dataString = "MATCH (n:Location) WHERE n.Number = " + number + " SET n.Clue_found = \"Yes\"" +
                        "SET n.Turn_investigated = " + turn;

                session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run(dataString);

                        return false;
                    }
                });
            }
        }

        return  answer;
    }

    public boolean hasClue(int number){
        try (Session session = driver.session()){

            String dataString = "MATCH (n:Location) WHERE n.Number = " + number + " RETURN n.Clue_found";

            return session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    if (result.single().get(0).toString().equals("\"Yes\"")){
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}

