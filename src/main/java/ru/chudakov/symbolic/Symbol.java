package ru.chudakov.symbolic;

import java.util.Map;

public interface Symbol extends Comparable<Symbol> {
    public Symbol add(Symbol symbol);

    public int size();

    public Symbol getFirst();
}
