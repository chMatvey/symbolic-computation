package ru.chudakov.symbolic;

import ru.chudakov.symbolic.visitor.OperationVisitor;

public abstract class AbstractSymbol implements Symbol {

    @Override
    public int compareTo(Symbol o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return null;
    }
}
