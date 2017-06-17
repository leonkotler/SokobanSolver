package searcher.searcher;

import searcher.searchable.Searchable;
import searcher.searchable.Solution;
import searcher.searchable.State;

import java.util.*;

public class Dijkstra<T> extends CommonSearcher<T> {

    private Set<State<T>> settledNodes;
    private Set<State<T>> unSettledNodes;
    private Map<State<T>, State<T>> predecessors;
    private Map<State<T>, Integer> distance;
    private State<T> goal;

    @Override
    public Solution search(Searchable s) {
        if(s.getGoalState().equals(s.getInitialState()))
            return new Solution();
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(s.getInitialState(), 0);
        unSettledNodes.add(s.getInitialState());
        while (unSettledNodes.size() > 0) {
            State<T> node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node, s);
        }
        Solution sol = backTrace(goal);
        return sol;
    }

    private void findMinimalDistances(State<T> node, Searchable<T> s) {
        List<State<T>> adjacentNodes = getNeighbors(node, s);
        for (State<T> target : adjacentNodes) {
            if (!settledNodes.contains(target)) {
                int tarDis = getShortestDistance(target);
                int nodeDis = getShortestDistance(node);
                if (tarDis > nodeDis) {
                    distance.put(target,(int)(node.getCost() + target.getCost()));
                    target.setCameFrom(node);
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }

            }
            if(target.equals(s.getGoalState()))
                goal = target;
        }
    }

    private List<State<T>> getNeighbors(State<T> node, Searchable<T> s) {
        List<State<T>> neighbors = new ArrayList<>();
        List<State<T>> allPossibleState = s.getAllPossibleStates(node);
        for (State<T> state : allPossibleState) {
            if (!isSettled(state)) {
                neighbors.add(state);
                //distance.put(state,(int)(state.getCost()));
            }
        }
        return neighbors;
    }

//    public Solution getPath(State<T> target) {
//        LinkedList<Action> path = new LinkedList<>();
//        State<T> step = predecessors.get(target);
//        // check if a path exists
//        if (step == null) {
//            return null;
//        }
//        path.add(step.getAction());
//        while (predecessors.get(step).getAction() != null) {
//            step = predecessors.get(step);
//            path.add(step.getAction());
//        }
//        Collections.reverse(path);
//        Solution sol = new Solution();
//        sol.setActions(path);
//        return sol;
//    }

    private State<T> getMinimum(Set<State<T>> vertexes) {
        State<T> minimum = null;
        for (State vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(State<T> vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(State<T> destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

}
