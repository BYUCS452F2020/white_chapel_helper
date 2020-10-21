package Models;

public class Connection {
  int from_node;
  int to_node;
  ConnectionType connectionType;

  public Connection(int from_noode, int to_node, String connType) throws Exception {
	this.from_node = from_noode;
	this.to_node = to_node;
	if(connType.toLowerCase() == ConnectionType.ALLEY.getType()){
	  connectionType= ConnectionType.ALLEY;
	}else if(connType.toLowerCase() == ConnectionType.STREET.getType()){
	  connectionType = ConnectionType.STREET;
	}else{
	  throw new Exception("invalid connection type provided: " + connType);
	}
  }
}

enum ConnectionType{
  ALLEY("alley"),
  STREET("street");

  private String connection_type;

  ConnectionType(String connection_type) {
	this.connection_type = connection_type;
  }

  public String getType() {
	return connection_type;
  }
}
