package measures; /**
 * Class Knowledge Base is a list of <Constraint> object
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class  KnowledgeBase {
    private List<Constraint> constraints;

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    public KnowledgeBase() {
        this.constraints = new ArrayList<>();
    }

    public KnowledgeBase(Collection<Constraint> constraints) {
        this();
        this.constraints.addAll(constraints);
    }

    public void add(Constraint c){
        constraints.add(c);
    }

    public void remove(Constraint c) {
        constraints.remove(c);
    }

    public boolean contain(Constraint c){
        return constraints.contains(c);
    }

    public boolean isEmpty(){
        return constraints.isEmpty();
    }
    @Override
    public String toString() {
        return "measures.KnowledgeBase{" +
                "constraints=" + constraints +
                '}';
    }
}
