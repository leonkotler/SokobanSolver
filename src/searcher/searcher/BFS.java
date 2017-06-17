package searcher.searcher;

import searcher.searchable.Searchable;
import searcher.searchable.Solution;
import searcher.searchable.State;

import java.util.ArrayList;
import java.util.HashSet;

public class BFS<T> extends CommonSearcher<T> {

    @Override
    public Solution search(Searchable<T> s) {
        openList.clear();
        evaluatedNodes = 0;
        openList.add(s.getInitialState());
        HashSet<State<T>> closedSet = new HashSet<>();
        State<T> n = null;

        while(openList.size() > 0){

            n = popOpenList();
            closedSet.add(n);

            if(n.equals(s.getGoalState()))
                return backTrace(n);

            ArrayList<State<T>> successors = s.getAllPossibleStates(n);
            for(State<T> state : successors){
                if(!closedSet.contains(state) && !openList.contains(state)){
                    state.setCameFrom(n);
                    state.setCost(state.getCost()+n.getCost());
                    openList.add(state);
                }
                else if(n.getCost() < state.getCameFrom().getCost()){
                    if(!openList.contains(state)) {
                        state.setCameFrom(n);
                        state.setCost(state.getCost()+n.getCost());
                        openList.add(state);
                    }
                    else{
                        openList.remove(state);
                        state.setCameFrom(n);
                        state.setCost(state.getCost()+n.getCost());
                        openList.add(state);
                    }
                }
            }
        }
        return backTrace(n);
    }



}
