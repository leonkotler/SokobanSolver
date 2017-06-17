package sokoban;


import searcher.searchable.Action;
import searcher.searchable.Position;
import searcher.searchable.State;

import java.util.ArrayList;

public class PlayerSearchable extends SokobanCommonSearchable {

    public PlayerSearchable(String filePath) {
        super(filePath);
    }

    @Override
    public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {

        ArrayList<State<Position>> list = new ArrayList<>();

        // Holds the player position
        Position sp = s.getState();

        // Look up for 4 possible option from sokoban position
        char up = getNeighbor(sp,Direction.UP);
        char down = getNeighbor(sp,Direction.DOWN);
        char left = getNeighbor(sp,Direction.LEFT);
        char right = getNeighbor(sp,Direction.RIGHT);

        // Adding all next possible states to the list
        if(up == ' ' || up == '@' || up == 'B'){
            State<Position> newState = createPossibleState(s,"move up");
            if (up == 'B'){
                if(sp.getRow()-2 == ' ' || sp.getRow()-2 == '@')
                    list.add(newState);
            }
            else
                list.add(newState);
        }
        if(down == ' ' || down == '@' || down == 'B'){
            State<Position> newState = createPossibleState(s,"move down");
            if (down == 'B'){
                if(sp.getRow()+2 == ' ' || sp.getRow()+2 == '@')
                    list.add(newState);
            }
            else
                list.add(newState);
        }
        if(right == ' ' || right == '@' || right == 'B'){
            State<Position> newState = createPossibleState(s,"move right");
            if (right == 'B'){
                if(sp.getCol()+2 == ' ' || sp.getCol()+2 == '@')
                    list.add(newState);
            }
            else
                list.add(newState);
        }
        if(left == ' ' || left == '@' || left == 'B'){
            State<Position> newState = createPossibleState(s,"move left");
            if (left == 'B'){
                if(sp.getCol()-2 == ' ' || sp.getCol()-2 == '@')
                    list.add(newState);
            }
            else
                list.add(newState);
        }
        return list;
    }

    public Position getSokoPosition(){
        for(int i = 0; i < grid.size(); i++){
            for(int j = 0; j < grid.get(i).length; j++){
                if(grid.get(i)[j] == 'A'){
                    return new Position(i,j);
                }
            }
        }
        return null;
    }

    private State<Position> createPossibleState(State<Position> currentState, String action){

       Position p = null;
       Position sp = currentState.getState();
       Action a = new Action(action);

       if (a.getAction().contains("up")) {
            p = new Position(sp.getRow()-1,sp.getCol());
       }
       else if (a.getAction().contains("down")) {
            p = new Position(sp.getRow()+1,sp.getCol());
       }
       else if (a.getAction().contains("right")) {
            p = new Position(sp.getRow(),sp.getCol()+1);
       }
       else if (a.getAction().contains("left")) {
            p = new Position(sp.getRow(),sp.getCol()-1);
       }
       return new State<>(p,1,currentState,a);
    }
}
