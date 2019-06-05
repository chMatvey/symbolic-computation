package ru.chudakov.symbolic;

public abstract class AbstractSymbol implements Symbol {

    @Override
    public int compareTo(Symbol o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }

    @Override
    public Symbol getFirst() {
        return this;
    }

    @Override
    public Symbol getLast() {
        return this;
    }

    @Override
    public int length() {
        return 1;
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
