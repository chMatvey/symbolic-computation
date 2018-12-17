package ru.chudakov.symbolic;

import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.term.Term;

public class LeafSymbol implements Symbol {
    private Term data;

    public LeafSymbol(Term data) {
        this.data = data;
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Symbol getFirst() {
        return this;
    }

    @Override
    public int compareTo(@NotNull Symbol o) {
        LeafSymbol leafSymbol = (LeafSymbol) o;
        return data.compareTo(leafSymbol.data);
    }
}
