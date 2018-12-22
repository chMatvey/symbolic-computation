package ru.chudakov.symbolic;

import org.jetbrains.annotations.NotNull;

public class PowerSymbol extends AbstractSymbol {
    protected Symbol base;
    protected Symbol index;

    @Override
    public int size() {
        return base.size();
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }

    @Override
    public int compareTo(@NotNull Symbol o) {
        return base.compareTo(o);
    }
}
