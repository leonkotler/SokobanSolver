package searcher.searcher;

import searcher.searchable.Action;
import searcher.searchable.Solution;
import searcher.searchable.State;

import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {

    protected PriorityQueue<State<T>> openList;
    protected int evaluatedNodes;

    public CommonSearcher(){
        openList = new PriorityQueue<>();
        evaluatedNodes = 0;
    }

    protected State popOpenList(){
        evaluatedNodes++;
        return openList.poll();
    }

    protected Solution backTrace(State<T> goalState) {
        LinkedList<Action> actions = new LinkedList<>();
        State<T> currState = goalState;
        while (currState.getCameFrom() != null) {
            actions.addFirst(currState.getAction());
            currState = currState.getCameFrom();
        }
        Solution sol = new Solution();
        sol.setActions(actions);
        return sol;
    }
}
