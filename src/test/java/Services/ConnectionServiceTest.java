package Services;

import DAOs.ConnectionDAO;
import Models.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConnectionServiceTest {

    Connection conn1, conn2, conn3, conn4, conn5, conn6, conn7, conn8, conn9, conn10;
    ConnectionDAO dao = new ConnectionDAO();

    ConnectionService service =  new ConnectionService();

    @BeforeEach
    void load() throws Exception{
        dao.clear();
        conn1 = new Connection(1,2,"street");
        conn2 = new Connection(1,3,"street");
        conn3 = new Connection(1,4,"street");
        conn4 = new Connection(2,3,"street");
        conn5 = new Connection(2,4,"street");
        conn6 = new Connection(2,5,"street");
        conn7 = new Connection(1,5,"alley");
        conn8 = new Connection(3,5,"alley");
        conn9 = new Connection(3,6,"street");
        conn10 = new Connection(1,6,"alley");

        dao.insertConnection(conn1);
        dao.insertConnection(conn2);
        dao.insertConnection(conn3);
        dao.insertConnection(conn4);
        dao.insertConnection(conn5);
        dao.insertConnection(conn6);
        dao.insertConnection(conn7);
        dao.insertConnection(conn8);
        dao.insertConnection(conn9);
        dao.insertConnection(conn10);
    }

    @Test
    void valid_conn1 (){
        Assertions.assertEquals(1, 1);
        Assertions.assertEquals(true, service.isValidMove(1, 2, "street"));
        Assertions.assertEquals(true, service.isValidMove(2, 4, "street"));
        Assertions.assertEquals(true, service.isValidMove(1, 6, "alley"));

    }

    @Test
    void invalid_conn1() {
        Assertions.assertEquals(false, service.isValidMove(5, 4, "street"));
        Assertions.assertEquals(false, service.isValidMove(6, 5, "street"));
        Assertions.assertEquals(false, service.isValidMove(1, 6, "street"));
    }

}
