package searcher.searchable;

public class Action {

    private String action;
    private Position pushFrom; // Player position precondition
    private Position currentBoxPos;

    private Position nextPosition; // Box post condition

    public Action(String action) {
        this.action = action;
    }

    public Action(String action, Position pushFrom, Position currentBoxPos, Position nextPosition){
        this.action = action;
        this.pushFrom = pushFrom;
        this.currentBoxPos = currentBoxPos;
        this.nextPosition = nextPosition;
    }

    public Position getPushFrom() {
        return pushFrom;
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    public Position getCurrentBoxPos() {
        return currentBoxPos;
    }

    public String getAction(){return this.action;}

    @Override
    public String toString() {
        return action;
    }

    @Override
    public boolean equals(Object obj) {
        Action a = (Action)obj;
        return a.action.equals(action);
    }

    @Override
    public int hashCode() {
        return action.hashCode();
    }
}
