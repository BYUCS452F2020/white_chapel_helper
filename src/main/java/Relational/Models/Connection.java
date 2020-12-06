package Relational.Models;

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

  @Override
  public String toString() {
	return "Connection{" +
			"from_node=" + from_node +
			", to_node=" + to_node +
			", connectionType=" + connectionType +
			'}';
  }
}

