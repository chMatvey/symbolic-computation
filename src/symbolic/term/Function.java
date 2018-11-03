package symbolic.term;

import symbolic.term.Terms;

public abstract class Function<T> extends Terms<T> {
    public abstract T getFunctionValue(T[] variables);
}
