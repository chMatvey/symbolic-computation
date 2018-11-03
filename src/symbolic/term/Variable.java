package symbolic.term;

import symbolic.term.Element;
import symbolic.term.TermTypes;

public class Variable<T> extends Element<T> {

    private String name;
    private T value;

    @Override
    public TermTypes getType() {
        return TermTypes.Variable;
    }

    @Override
    public String getExpressionName() {
        return name;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        value = value;
    }
}
