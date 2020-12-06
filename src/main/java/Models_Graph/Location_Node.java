package Models_Graph;


import org.neo4j.driver.types.Node;

public class Location_Node {
    private final int Number;
    private int Turn_Investigated;
    private boolean Clue_Found;
    private boolean Jack_Is_Here;

    public Location_Node(int number) {
        Number = number;
        Turn_Investigated = 0;
        Clue_Found = false;
        Jack_Is_Here = false;
    }

    public Location_Node(int number, int TI, boolean CF, boolean JIH) {
        Number = number;
        Turn_Investigated = TI;
        Clue_Found = CF;
        Jack_Is_Here = JIH;
    }

    public int getNumber() {
        return Number;
    }

    public int getTurn_Investigated() {
        return Turn_Investigated;
    }

    public void setTurn_Investigated(int turn_Investigated) {
        Turn_Investigated = turn_Investigated;
    }

    public boolean isClue_Found() {
        return Clue_Found;
    }

    public void setClue_Found(boolean clue_Found) {
        Clue_Found = clue_Found;
    }

    public boolean isJack_Is_Here() {
        return Jack_Is_Here;
    }

    public void setJack_Is_Here(boolean jack_Is_Here) {
        Jack_Is_Here = jack_Is_Here;
    }

    @Override
    public String toString() {
        return "Location_Node{" +
                "Number=" + Number +
                ", Turn_Investigated=" + Turn_Investigated +
                ", Clue_Found=" + Clue_Found +
                ", Jack_Is_Here=" + Jack_Is_Here +
                '}';
    }
}
