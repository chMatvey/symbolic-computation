package ru.chudakov.symbolic;

import ru.chudakov.symbolic.term.Term;

public abstract class OperandSymbol extends AbstractSymbol {
    protected Double data;

    public OperandSymbol(Double data) {
        this.data = data;
    }

    public abstract int getPriority();

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }

    @Override
    public int compareTo(Symbol o) {
        if (this.getPriority() < o.getPriority())
            return -1;
        else if (this.getPriority() == this.getPriority())
            return 0;
        else
            return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Symbol symbol = (Symbol) obj;
        if (size() != symbol.size())
            return false;
        OperandSymbol operand = (OperandSymbol) symbol;
        return data.equals(operand.data);
    }
}
