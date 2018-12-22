package ru.chudakov.symbolic.operation;

import ru.chudakov.symbolic.AbstractSymbol;
import ru.chudakov.symbolic.Symbol;

public abstract class FunctionSymbol extends OperationSymbol {
    private Symbol argument;

    public FunctionSymbol(Symbol argument) {
        this.argument = argument;
    }

    @Override
    public int getPriority() {
        return -4;
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            FunctionSymbol function = (FunctionSymbol) o;
            result = this.toString().compareTo(function.toString());
        }
        return result;
    }
}
