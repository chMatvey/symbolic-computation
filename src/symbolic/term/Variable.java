package symbolic.term;

public class Variable<T extends java.lang.Number> extends Value<T> {

    private String name;
    private T value;

    public Variable(String name){
        this.name = name;
    }

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
