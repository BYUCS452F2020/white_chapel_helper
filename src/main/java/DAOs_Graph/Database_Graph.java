package DAOs_Graph;

import DAOs.DataAccessException;
import DAOs.Database;
import org.neo4j.driver.*;
import Models_Graph.Location_Node;

import java.sql.Connection;

public class Database_Graph implements AutoCloseable {

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
        try {
            driver.close();
        }
        catch (Exception e){
            System.out.println("error in closing" + e);
        }
    }

    //to test the database
    public static void main(String[] args){
        Database_Graph db = new Database_Graph();

            Driver driver = db.getDriver();
            if(driver != null) {
                System.out.println("it worked!");
            }

        // Tests the retrieval of node data
        // Tests the retrieval of node data
        try (Session session = driver.session()) {
            //String dataString = "MATCH (node:Location) WHERE node.Number = 1 RETURN node.Turn_investigated";
            String dataString = "MATCH (node:Location) WHERE node.Number = 2 RETURN node.Number";//, node.Turn_Investigated, node.Clue_Found, node.Jack_Is_Here";

            Double result = session.writeTransaction(new TransactionWork<Double>() {
                @Override
                public Double execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    System.out.println(result.single().get(0).asInt());
                    return 2.0;
                    //System.out.println(result.single().get(0).asInt());
                    //return "all done!";
                }
            });
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
