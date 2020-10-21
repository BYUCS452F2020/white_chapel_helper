package Models;

public enum ConnectionType {
  STREET("street"),
  ALLEY("alley");

  private final String conn_type;

  ConnectionType(String conn) {
    this.conn_type = conn;
  }

  public String getConn_type() {
	return conn_type;
  }

  @Override
  public String toString() {
	return  conn_type;
  }
}
