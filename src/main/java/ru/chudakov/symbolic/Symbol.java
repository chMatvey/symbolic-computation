package ru.chudakov.symbolic;

import java.util.Collection;

public interface Symbol<T extends Number> extends Comparable<Symbol> {
    public void add(Symbol symbol);

    public void mul(Symbol symbol);

    public void power(Symbol symbol);
}
