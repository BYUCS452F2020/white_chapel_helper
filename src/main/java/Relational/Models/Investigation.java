package Relational.Models;

public class Investigation {
  private final int node;
  private final int turn_investigated;
  private final Boolean has_clue;

  public Investigation(int node, int turn_investigated, Boolean has_clue) {
	this.node = node;
	this.turn_investigated = turn_investigated;
	this.has_clue = has_clue;
  }

  public int getNode() {
	return node;
  }

  public int getTurn_investigated() {
	return turn_investigated;
  }

  public Boolean getHas_clue() {
	return has_clue;
  }
}
