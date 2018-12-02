package symbolic.term;

public interface Term<T extends Number> {
    public TermTypes getType();

    public String getExpressionName();
}
