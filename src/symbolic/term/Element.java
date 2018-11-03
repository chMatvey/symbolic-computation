package symbolic.term;

import symbolic.term.Terms;

public abstract class Element<T> extends Terms<T> {
    public abstract T getValue();
    public abstract void setValue(T value);
}
