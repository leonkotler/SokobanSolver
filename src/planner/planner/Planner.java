package planner.planner;

import planner.plannable.PlanAction;
import planner.plannable.Plannable;

import java.util.List;

public interface Planner {

    List<PlanAction> computePlan(Plannable plannable, HeuristicMethods h);

}
