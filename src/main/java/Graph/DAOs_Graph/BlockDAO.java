package Graph.DAOs_Graph;

import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.List;

public class BlockDAO {

    private final databaseGraph ds = new databaseGraph();
    private final Driver driver = ds.getDriver();

    // this creates a City_Block Node. this represents the different groupings of Nodes that can be connected through Alleys
    // the City_block Node has values for: Number, the number the Block is

    public void addBlock(int number){

        try (Session session = driver.session()){


            String dataString = "CREATE (:City_block {Number:" + number + "})";

            session.writeTransaction(transaction -> {
                Result result = transaction.run(dataString);
                return result.toString();
            });
        }
    }

    public void deleteBlocks(){
        try (Session session = driver.session()){

            String dataString = "MATCH (n:City_block) DETACH DELETE n";

            session.writeTransaction(transaction -> {
                Result result = transaction.run(dataString);
                return result.toString();
            });
        }
    }

    public void addAlleyConnection(int city_block, int node){
        try (Session session = driver.session()) {
            String dataString = "MATCH (a:Block), (b:Location) WHERE a.Number = " + city_block +
                    " AND b.Number = " + node + " CREATE (a)-[:STREET]->(b)";

            session.writeTransaction(transaction -> {
                Result result = transaction.run(dataString);
                return result.toString();
            });
        }
    }
    
    public List<Integer> getAllConnections(int number){
        List<Integer> returnList = new ArrayList<>();
        try (Session session = driver.session()){

            String dataString = "MATCH (n:City_block {Number:" + number + "})-[:ALLEY]->(results) RETURN results.Number";

            session.writeTransaction((TransactionWork<String>) transaction -> {
                Result result = transaction.run(dataString);

                while (result.hasNext()){
                    returnList.add(result.next().get(0).asInt());
                }
                return null;
            });
        }
        return returnList;
    }

    public String getConnectedLocations(int cityBlock){
        try (Session session = driver.session()) {
            String dataString = "MATCH (block:City_block {Number:" + cityBlock + "}) RETURN block.Number";

            return session.writeTransaction(transaction -> {
                Result result = transaction.run(dataString);
                return result.single().get(0).asString();
            });

        }
    }
}
