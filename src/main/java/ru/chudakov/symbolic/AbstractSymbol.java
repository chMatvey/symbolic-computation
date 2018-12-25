package ru.chudakov.symbolic;

import ru.chudakov.symbolic.visitor.OperationVisitor;

public abstract class AbstractSymbol implements Symbol {

    @Override
    public Symbol add(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return null;
    }

    @Override
    public int compareTo(Symbol o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }

    @Override
    public int hashCode() {
        return getPriority();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        AbstractSymbol symbol = (AbstractSymbol) obj;
        return getPriority() == symbol.getPriority();
    }
}
