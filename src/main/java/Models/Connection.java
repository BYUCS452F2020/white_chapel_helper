package Models;

public class Connection {
  private final int from_node;
  private final int to_node;
  private final ConnectionType connectionType;

  public Connection(int from_node, int to_node, String connType) throws Exception {
	this.from_node = from_node;
	this.to_node = to_node;
	if(connType.toLowerCase().equals(ConnectionType.ALLEY.getType())){
	  connectionType= ConnectionType.ALLEY;
	}else if(connType.toLowerCase().equals(ConnectionType.STREET.getType())){
	  connectionType = ConnectionType.STREET;
	}else{
	  throw new Exception("invalid connection type provided: " + connType);
	}
  }

  public int getFrom_node() {
	return from_node;
  }

  public int getTo_node() {
	return to_node;
  }

  public ConnectionType getConnectionType() {
	return connectionType;
  }
}

enum ConnectionType{
  ALLEY("alley"),
  STREET("street");

  private final String connection_type;

  ConnectionType(String connection_type) {
	this.connection_type = connection_type;
  }

  public String getType() {
	return connection_type;
  }
}
