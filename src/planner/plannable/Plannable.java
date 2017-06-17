package planner.plannable;

import planner.predicate.And;
import planner.predicate.Predicate;

import java.util.List;

public interface Plannable {

    And getGoal();
    And getKnowledgeBase();
    List<PlanAction> getSatisfyingActions(Predicate p);

}
