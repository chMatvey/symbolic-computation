package symbolic.term;


public abstract class Value<T extends java.lang.Number> extends Terms<T> {
    public abstract T getValue();
    public abstract void setValue(T value);
}

