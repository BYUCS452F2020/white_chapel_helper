package Graph.DAOs_Graph;

import org.neo4j.driver.*;

import java.util.Vector;

public class BlockDAO {

    private Database_Graph ds = new Database_Graph();
    private Driver driver = ds.getDriver();

    // this creates a City_Block Node. this represents the different groupings of Nodes that can be connected through Alleys
    // the City_block Node has values for: Number, the number the Block is

    public void addBlock(int number){

        try (Session session = driver.session()){


            String dataString = "CREATE (:City_block {Number:" + number + "})";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.toString();
                }
            });
        }
    }

    public boolean testBlock(int number){
        return true;
    }

    public void deleteBlocks(){
        try (Session session = driver.session()){

            String dataString = "MATCH (n:City_block) DETACH DELETE n";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.toString();
                }
            });
        }
    }



    public void addAlleyConnection(int city_block, int node){
        try (Session session = driver.session()) {
            String dataString = "MATCH (a:Block), (b:Location) WHERE a.Number = " + city_block +
                    " AND b.Number = " + node + " CREATE (a)-[:STREET]->(b)";

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

            String dataString = "MATCH (n:City_block {Number:" + number + "})-[:ALLEY]->(results) RETURN results.Number";

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

    //TODO can send the request to the database, but haven't figured out how to parse the answer
    //public Vector<int> getConnectedLocations(int city_block){
    //    try (Session session = driver.session()) {
    //        String dataString = "MATCH (block:City_block {Number:" + city_block + "}) RETURN block.Number";

    //        String answer = session.writeTransaction(new TransactionWork<String>() {
    //            @Override
    //           public String execute(Transaction transaction) {
    //                Result result = transaction.run(dataString);
    //                return result.single().get(0).asString();
    //            }
    //        });

    //    }
    //}
}
