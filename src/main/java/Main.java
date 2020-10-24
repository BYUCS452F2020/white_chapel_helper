import DAOs.ConnectionDAO;
import Models.Connection;

public class Main {
  public static void main(String[] args){
	ConnectionDAO dao = new ConnectionDAO();
	Boolean success = dao.insertConnection(1,2,"street");
	Connection conn = dao.getConnection(1,2);
	System.out.println(success);
	System.out.println(conn.toString());

  }
}
