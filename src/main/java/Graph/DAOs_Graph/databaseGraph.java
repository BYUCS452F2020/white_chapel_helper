package Graph.DAOs_Graph;

import org.neo4j.driver.*;

public class databaseGraph implements AutoCloseable {

    private Driver driver;

    public Driver getDriver() {
        if (driver == null) {
            // I was able to connect to my own local neo4j database using the below format
            driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("admin", "admin"));
        }
        return driver;
    }

    @Override
    public void close() {
        //TODO test if functions need to manage their own sessions
        try {
            driver.close();
        }
        catch (Exception e){
            System.out.println("error in closing" + e);
        }
    }

    public void clear_graph() {
        Driver driver = getDriver();
        try (Session session = driver.session()){
            String dataString = "MATCH (n) DETACH DELETE n";

            session.writeTransaction(transaction -> {
                Result result = transaction.run(dataString);
                return result.toString();
            });
        }

    }

    public void addNode(int number){

        try (Session session = driver.session()){


            String dataString = "CREATE (:Location {Number:" + number + ", Jack_visited:\"No\"," +
                    " Turn_investigated:0, Clue_found:\"No\"})";

            session.writeTransaction(transaction -> {
                Result result = transaction.run(dataString);
                return result.toString();
            });
        }
    }
}
