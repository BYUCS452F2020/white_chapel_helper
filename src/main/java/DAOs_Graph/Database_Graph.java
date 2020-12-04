package DAOs_Graph;

import DAOs.DataAccessException;
import DAOs.Database;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class Database_Graph implements AutoCloseable {

    private Driver driver;

    public Driver getDriver() {
        driver = GraphDatabase.driver("bolt://localhost:7687");
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
    }
}
