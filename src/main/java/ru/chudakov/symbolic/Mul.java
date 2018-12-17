package ru.chudakov.symbolic;

import java.util.Collection;

public class Mul extends Operation {
    public Mul(Collection<Symbol> nodes, Collection<Symbol> coefficients) {
        super(nodes, coefficients);
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public Symbol getFirst() {
        return super.getFirst();
    }
}
