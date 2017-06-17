package searcher.searchable;

import java.util.LinkedList;

public class Solution {

    private LinkedList<Action> actions = new LinkedList<>();
    
    public LinkedList<Action> getActions() {return actions;}
    public void setActions(LinkedList<Action> actions) {this.actions = actions;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Action a : actions) {
            sb.append(a.getAction()).append("\n");
        }
        return sb.toString();
    }


}
