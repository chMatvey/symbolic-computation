package symbolic.term;

public class Number<T extends java.lang.Number> extends Value<T> {

    private T data;

    public Number(T data){
        this.data = data;
    }

    @Override
    public TermTypes getType() {
        return TermTypes.Number;
    }

    @Override
    public String getExpressionName() {
        return data.toString();
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
