package ru.chudakov.symbolic.operation;

import ru.chudakov.symbolic.Symbol;

public class PowerSymbol extends OperationSymbol {
    private Symbol base;
    private Symbol index;

    public PowerSymbol(Symbol base, Symbol index) {
        this.base = base;
        this.index = index;
    }

    @Override
    public int getPriority() {
        return -3;
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            result = base.compareTo(((PowerSymbol) o).base);
        } else {
            result = base.compareTo(o);
        }
        return result;
    }
}
