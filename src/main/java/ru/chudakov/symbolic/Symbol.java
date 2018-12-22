package ru.chudakov.symbolic;

import java.util.Iterator;
import java.util.Map;

public interface Symbol extends Comparable<Symbol> {
    public int size();

    public Symbol add(Symbol symbol);
}
