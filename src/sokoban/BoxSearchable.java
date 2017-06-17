package sokoban;


import searcher.searchable.Action;
import searcher.searchable.Position;
import searcher.searchable.State;

import java.util.ArrayList;

public class BoxSearchable extends SokobanCommonSearchable {

    public BoxSearchable(String filePath) {
        super(filePath);
    }

    @Override
    public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {

        ArrayList<State<Position>> list = new ArrayList<>();
        Position bp = s.getState(); // Holds the box position

        // look up of 4 possible option from box position
        char up = getNeighbor(bp,Direction.UP);
        char down = getNeighbor(bp,Direction.DOWN);
        char left = getNeighbor(bp,Direction.LEFT);
        char right = getNeighbor(bp,Direction.RIGHT);

        // Clearing the neighbor position for the current state (because original grid is not update right now)
        if(s.getCameFrom() != null){
            String action = s.getAction().getAction();
            if(action.contains("up")){
                down = ' ';
            }
            else if(action.contains("down")){
                up = ' ';
            }
            else if(action.contains("right")){
                left = ' ';
            }
            else if(action.contains("left")){
                right = ' ';
            }
        }

        // Adding all next possible states to the list
        int row = bp.getRow();
        int col = bp.getCol();
        if((up == ' ' || up == '@' || up == 'A') && (down == ' ' || down == '@' || down == 'A')){
            if(!(up == 'A')){
                State<Position> newState1 = new State<>(
                        new Position(row-1,col),1,s,
                        new Action("move up", new Position(row+1,col),bp,
                                new Position(row-1,col)));
                list.add(newState1);
            }
            if(!(down == 'A')){
                State<Position> newState2 = new State<>(
                        new Position(row+1,col),1,s,
                        new Action("move down",new Position(row-1,col),bp,
                                new Position(row+1,col)));
                list.add(newState2);
            }
        }
        if((right == ' ' || right == '@' || right == 'A') && (left == ' ' || left == '@' || left == 'A')){
            if(!(right == 'A')){
                State<Position> newState1 = new State<>(
                        new Position(row,col+1),1,s,
                        new Action("move right",new Position(row,col-1),bp,
                                new Position(row,col+1)));
                list.add(newState1);
            }
            if(!(left == 'A')){
                State<Position> newState2 = new State<>(
                        new Position(row,col-1),1,s,
                        new Action("move left",new Position(row,col+1),bp,
                                new Position(row,col-1)));
                list.add(newState2);
            }
        }
        return list;
    }
}
