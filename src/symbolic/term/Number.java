package symbolic.term;

public class Number<T> extends Element<T> {

    private T data;

    @Override
    public TermTypes getType() {
        return TermTypes.Number;
    }

    @Override
    public String getExpressionName() {
        return null;
    }

    @Override
    public T getValue() {
        return data;
    }

    @Override
    public void setValue(T value) {
        data = value;
    }
}
