package symbolic.term;

public class Variable<T extends Number> extends Magnitude<T> {

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
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Variable variable = (Variable) obj;
        return name.equals(variable.name);
    }
}
