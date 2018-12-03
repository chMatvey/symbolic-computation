package ru.chudakov.symbolic.term;

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

    @Override
    public int compareTo(Term o) {
        int result = super.compareTo(o);
        if (result == 0){
            Variable variable = (Variable) o;
            result = name.compareTo(variable.name);
        }
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
