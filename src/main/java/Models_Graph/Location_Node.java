package Models_Graph;

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
