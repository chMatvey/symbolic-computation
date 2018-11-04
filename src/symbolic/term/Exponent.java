package symbolic.term;

import java.util.List;

public class Exponent<T extends java.lang.Number> extends Function<T> {

    private static final String name = "exp";

    @Override
    public TermTypes getType() {
        return TermTypes.Exponent;
    }

    @Override
    public String getExpressionName() {
        return name;
    }

    @Override
    public T getFunctionValue(List<T> variables) {
        return null;
    }
}
