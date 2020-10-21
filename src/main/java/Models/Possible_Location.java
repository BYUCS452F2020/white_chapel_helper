package Models;

public class Possible_Location {
  int turn;
  int node;

  public Possible_Location(int turn, int node) {
	this.turn = turn;
	this.node = node;
  }

  public int getTurn() {
	return turn;
  }

  public int getNode() {
	return node;
  }
}
