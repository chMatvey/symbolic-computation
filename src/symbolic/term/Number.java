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

    public static Number add(Number a, Number b){
        Number result = null;
        if(a.getValue() instanceof Double || b.getValue() instanceof Double){
            result = new Number<Double>(a.getValue().doubleValue() + b.getValue().doubleValue());
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof Integer){
            result = new Number<Integer>(a.getValue().intValue() + b.getValue().intValue());
        }
        return result;
    }
}
