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
        try (Session session = driver.session()) {
            //String dataString = "MATCH (node:Location) WHERE node.Number = 1 RETURN node.Turn_investigated";
            String dataString = "MATCH (node:Location_Node) WHERE node.Number = 87 RETURN node.Number";//, node.Turn_Investigated, node.Clue_Found, node.Jack_Is_Here";

            Location_Node newNode = session.writeTransaction(new TransactionWork<Location_Node>() {
                @Override
                public Location_Node execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    //System.out.println(result.single().get(0).asInt());
                    //return "all done!";
                    Location_Node locNode = null;
                    try {
                        int testInt = result.single().get(0).asInt();
                        int test2 = result.single().get(1).asInt();

                        locNode = new Location_Node(result.single().get(0).asInt(),
                                                    result.single().get(1).asInt(),
                                                    result.single().get(2).asBoolean(),
                                                    result.single().get(3).asBoolean());

                        return locNode;
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                }
            });
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
