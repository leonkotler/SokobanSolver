package planner.plannable;

import planner.predicate.And;

public class PlanAction {

    private String name;
    private And preConditions;
    private And postConditions;

    public PlanAction(String name) {
        this.name = name;
        this.postConditions = null;
        this.postConditions = null;
    }

    public PlanAction(String name, And preConditions, And postConditions) {
        this.preConditions = preConditions;
        this.postConditions = postConditions;
        this.name = name;
    }

    public And getPreCondition(){
        return this.preConditions;
    }

    public And getPostCondition(){
        return this.postConditions;
    }

    @Override
    public String toString() {
        return name;
    }
}
