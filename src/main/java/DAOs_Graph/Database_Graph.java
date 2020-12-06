package DAOs_Graph;

import DAOs.DataAccessException;
import DAOs.Database;
import org.neo4j.driver.*;

import java.sql.Connection;

public class Database_Graph implements AutoCloseable {

    private Driver driver;

    public Driver getDriver() {
        if (driver == null) {
            // I was able to connect to my own local neo4j database using the below format
            driver = GraphDatabase.driver("bolt://localhost:7687/WhiteChapel_Test", AuthTokens.basic("admin", "admin"));
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
            String dataString = "MATCH (node:Location) WHERE node.Number = 1 RETURN node.Turn_investigated";

            String answer = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    System.out.println(result.single().get(0).asInt());
                    return "all done!";
                }
            });

            System.out.println(answer);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
