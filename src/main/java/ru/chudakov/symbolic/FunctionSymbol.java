package ru.chudakov.symbolic;

import ru.chudakov.symbolic.function.Function;

public class FunctionSymbol extends AbstractSymbol {
    protected Symbol base;
    protected Function function;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }

    @Override
    public int compareTo(Symbol o) {
        return 0;
    }
}
