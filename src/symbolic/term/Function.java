package symbolic.term;

import java.util.List;

public abstract class Function<T extends java.lang.Number> extends Terms<T> {
    public abstract T getFunctionValue(List<T> variables);
}
