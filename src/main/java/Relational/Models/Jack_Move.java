package Relational.Models;

public class Jack_Move {
  private final int turn;
  private final MoveType move_type;
  private final int dest_node;

  public Jack_Move(int turn, int dest_node, String move_type) throws Exception{
	this.turn = turn;
	this.dest_node = dest_node;
	if(move_type.equals(MoveType.NORM.getType())){
	  this.move_type = MoveType.NORM;
	}else if(move_type.equals(MoveType.CARRIAGE.getType())){
	  this.move_type = MoveType.CARRIAGE;
	}else if(move_type.equals(MoveType.ALLEY.getType())){
	  this.move_type = MoveType.ALLEY;
	}else{
	  throw new Exception("invalid move type: " + move_type);
	}
  }

  public int getTurn() {
	return turn;
  }

  public MoveType getMove_type() {
	return move_type;
  }

  public int getDest_node() {
	return dest_node;
  }

  @Override
  public String toString() {
	return "Jack_Move{" +
			"turn=" + turn +
			", move_type=" + move_type +
			", dest_node=" + dest_node +
			'}';
  }
}

