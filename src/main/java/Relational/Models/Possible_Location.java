package Relational.Models;

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

  @Override
  public boolean equals(Object o) {
	if(this == o) return true;
	if(o == null || getClass() != o.getClass()) return false;
	Possible_Location that = (Possible_Location) o;
	return turn == that.turn &&
			node == that.node;
  }

  public String toString(){
      return "turn = " + turn + " node = " + node + "\n";
  }
}
