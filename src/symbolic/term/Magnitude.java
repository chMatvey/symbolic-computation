package symbolic.term;

public abstract class Magnitude<T extends Number> extends AbstractTerm<T> {
    public abstract T getValue();
    public abstract void setValue(T value);
}

