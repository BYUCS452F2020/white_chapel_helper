package DAOs_Graph;

import DAOs.Database;
import org.neo4j.driver.*;

public class BlockDAO {

    private Database_Graph ds = new Database_Graph();
    private Driver driver = ds.getDriver();

    // this creates a City_Block Node. this represents the different groupings of Nodes that can be connected through Alleys
    // the City_block Node has values for: Number, the number the Block is

    public void addBlock(int number){

        try (Session session = driver.session()){


            String dataString = "CREATE (Block_" + number + ":City_block {Number:" + number + "})";

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
            String dataString = "(Block" + city_block + ")-[:ALLEY]-(Node_" + node + ")";

            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    Result result = transaction.run(dataString);
                    return result.toString();
                }
            });
        }
    }

    //TODO can send the request to the database, but haven't figured out how to parse the answer
    //public Vector<int> getConnectedLoactions(int city_block){
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
