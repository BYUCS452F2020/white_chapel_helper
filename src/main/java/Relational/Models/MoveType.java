package Relational.Models;

public enum MoveType {
  NORM("normal"),
  CARRIAGE("carriage"),
  ALLEY("alley");

  private final String move_type;

  MoveType(String connection_type) {
	this.move_type = connection_type;
  }

  public String getType() {
	return move_type;
  }

  @Override
  public String toString() {
	return move_type;
  }
}
