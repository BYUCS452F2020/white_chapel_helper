package Relational.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import org.neo4j.driver.*;

public class Database {
    private Connection conn;

    //The path assumes you start in the root of your project unless given a non-relative path
    final String CONNECTION_URL = "jdbc:sqlite:White_Chapel_DB.db";
            // Nathan's URL:
            //"jdbc:sqlite:/Users/ngill/Documents/GitHub/white_chapel_helper/DB_files/White_Chapel_DB.db";

            // Joanna's URL "jdbc:sqlite:/Users/user/Documents/fall20/Database/white_chapel_helper/src/main/resources/White_Chapel_DB.db";
            // Adam's URL "jdbc:sqlite:/Users/User/Documents/School/2020_Fall/Project/White_Chapel_DB.db";

    // a test that getting a connection works
    public static void main(String[] args){
        Database db = new Database();
        try {
            Connection conn = db.getConnection();
            System.out.println("it worked!");
        } catch(DataAccessException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    private Connection openConnection() throws DataAccessException {
        try {
            // Open a database connection to the file given in the path
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return conn;
    }

    public Connection getConnection() throws DataAccessException {
        if(conn == null) {
            return openConnection();
        } else {
            return conn;
        }
    }

    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                //This will commit the changes to the database
                conn.commit();
            } else {
                //If we find out something went wrong, pass a false into closeConnection and this
                //will rollback any changes we made during this connection
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new DataAccessException("Unable to close database connection");
            } catch(DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        }
    }

    public void clearTables() throws DataAccessException {

        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate("DELETE FROM CONNECTIONS");
            stmt.executeUpdate("DELETE FROM JACK_MOVES");
            stmt.executeUpdate("DELETE FROM INVESTIGATIONS");
            stmt.executeUpdate("DELETE FROM POSSIBLE_LOCATIONS");
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}

