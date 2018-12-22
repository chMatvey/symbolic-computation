package ru.chudakov.symbolic;

import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.term.Term;

public class OperandSymbol extends AbstractSymbol {
    private Term data;

    public OperandSymbol(Term data) {
        this.data = data;
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
        OperandSymbol operand = (OperandSymbol) o.getFirst();
        return data.compareTo(operand.data);
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
        OperandSymbol operand = (OperandSymbol) symbol.getFirst();
        return data.equals(operand.data);
    }
}
