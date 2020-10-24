package Models;

public enum ConnectionType {
  ALLEY("alley"),
  STREET("street");

  private final String connection_type;

  ConnectionType(String connection_type) {
	this.connection_type = connection_type;
  }

  public String getType() {
	return connection_type;
  }

  @Override
  public String toString() {
	return connection_type;
  }
}
