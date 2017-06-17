package planner.predicate;

import java.util.HashSet;

public class And extends Predicate {

    private HashSet<Predicate> predicates;

    public And(Predicate... predicates){
        super("and","","");
        this.predicates = new HashSet<>();
        if(predicates != null){
            for(Predicate p : predicates){
                this.predicates.add(p);
                updateDescription();
            }
        }
    }

    public void addPredicate(Predicate p){
        if(predicates == null)
            predicates = new HashSet<>();
        predicates.add(p);
        updateDescription();
    }

    public HashSet<Predicate> getPredicates(){
        return this.predicates;
    }

    @Override
    public boolean satisfies(Predicate p){
        for(Predicate pr : predicates)
            if(pr.satisfies(p))
                return true;
        return false;
    }

    public boolean satisfies(And clause){
        for(Predicate p : clause.predicates){
            if(!satisfies(p))
                return false;
        }
        return true;
    }

    public void update(And effect){
        effect.predicates.forEach((Predicate p)->this.predicates.removeIf((Predicate pr)-> p.contradicts(pr)));
        predicates.addAll(effect.predicates);
        updateDescription();
    }

    public void updateDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(Predicate p : predicates){
            sb.append(p.toString());
            sb.append(" ∧ ");
        }
        sb.delete(sb.length()-3,sb.length());
        sb.append("}");
        value = sb.toString();
    }

    @Override
    public String toString() {
        return value;
    }
}
