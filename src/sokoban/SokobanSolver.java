package sokoban;


import planner.plannable.PlanAction;
import planner.planner.HeuristicMethods;
import planner.planner.Planner;

import java.util.List;

public class SokobanSolver {

    private SokobanPlannable plannable;
    private Planner planner;
    private HeuristicMethods h;

    public SokobanSolver(SokobanPlannable sokoPlannable, Planner planner) {
        this.planner = planner;
        this.plannable = sokoPlannable;

    }

    public List<PlanAction> solve(){
        if(h == null)
            return planner.computePlan(plannable,null);
        else
            return planner.computePlan(plannable,h);
    }


    public void setHeuristic(HeuristicMethods h) {
        this.h = h;
    }
}

