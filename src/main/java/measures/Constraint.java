package measures; /**
 * Class measures.Constraint, each object of this class contain two properties: Statement and Probability
 */

import java.util.Objects;

public class Constraint {
    private String statement;
    private double probability;

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Constraint(String statement, double probability) {
        this.statement = statement;
        this.probability = probability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constraint that = (Constraint) o;
        return Objects.equals(statement, that.statement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statement);
    }

    @Override
    public String toString() {
        return statement + ":" + probability;
    }
}
