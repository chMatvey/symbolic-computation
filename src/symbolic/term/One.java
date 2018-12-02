package symbolic.term;

public class One extends Term {
    @Override
    public TermTypes getType() {
        return TermTypes.Number;
    }

    @Override
    public String getExpressionName() {
        return this.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || getClass() == obj.getClass();
    }
}
