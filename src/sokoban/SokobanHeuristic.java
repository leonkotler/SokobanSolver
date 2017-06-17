package sokoban;

import planner.planner.HeuristicMethods;
import planner.predicate.And;
import planner.predicate.Predicate;

import java.util.HashSet;

public class SokobanHeuristic implements HeuristicMethods {

    @Override
    public And getGoal(And knowledgeBase) {

        And goal = new And(null);
        HashSet<SokobanPredicate> boxes = new HashSet<>();
        HashSet<SokobanPredicate> targets = new HashSet<>();

        // Collect boxes and targets predicates from knowledge base
        for (Predicate p : knowledgeBase.getPredicates()) {
            if (p.getType().equals("targetAt")) {
                targets.add(new SokobanPredicate(p));
            } else if (p.getType().equals("boxAt")) {
                boxes.add(new SokobanPredicate(p));
            }
        }
        // Searching for the shortest path for each box
        for (SokobanPredicate box : boxes) {
            int boxRow = box.getPosition().getRow();
            int boxCol = box.getPosition().getCol();
            double minDistance = Double.MAX_VALUE;
            SokobanPredicate boxGoal = new SokobanPredicate("boxAt",box.getId(),null);
            SokobanPredicate minTarget = null;
            for (SokobanPredicate target : targets) {
                int tarRow = target.getPosition().getRow();
                int tarCol = target.getPosition().getCol();
                // Compute the distance between box and target
                double distance = Math.sqrt(Math.pow(boxRow-tarRow,2)+Math.pow(boxCol-tarCol,2));
                if(distance < minDistance){
                    minDistance = distance;
                    minTarget = target;
                    boxGoal.setValue(target.getValue()); // Update boxGoal predicate to the desire target position
                }
            }
            targets.remove(minTarget); // Removing the used target from the set to prevent duplicates
            goal.addPredicate(boxGoal);

        }
        return goal;
    }


}
