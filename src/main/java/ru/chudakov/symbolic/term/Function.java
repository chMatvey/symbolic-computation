package ru.chudakov.symbolic.term;

import java.util.List;

public abstract class Function<T extends Number> extends AbstractTerm<T> {
    public abstract Number getFunctionValue(T variables);
}
