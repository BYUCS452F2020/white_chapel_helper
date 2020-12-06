package Models_Graph;


import org.neo4j.driver.types.Node;

public class Location_Node {
    private final int Number;
    private int Turn_investigated;
    private boolean Clue_found;
    private boolean Jack_Is_Here; //Don't have yet but can add as we go?
    private boolean Jack_visited;

    public Location_Node(int number) {
        Number = number;
        Turn_investigated = 0;
        Clue_found = false;
        Jack_Is_Here = false;
    }

    public Location_Node(int number, int TI, boolean CF, boolean JIH) {
        Number = number;
        Turn_investigated = TI;
        Clue_found = CF;
        Jack_Is_Here = JIH;
    }

    public int getNumber() {
        return Number;
    }

    public int getTurn_investigated() {
        return Turn_investigated;
    }

    public void setTurn_investigated(int turn_investigated) {
        Turn_investigated = turn_investigated;
    }

    public boolean isClue_found() {
        return Clue_found;
    }

    public void setClue_found(boolean clue_found) {
        Clue_found = clue_found;
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
                ", Turn_Investigated=" + Turn_investigated +
                ", Clue_Found=" + Clue_found +
                ", Jack_Is_Here=" + Jack_Is_Here +
                '}';
    }
}
