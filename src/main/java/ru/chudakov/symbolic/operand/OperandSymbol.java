package ru.chudakov.symbolic.operand;

import ru.chudakov.symbolic.AbstractSymbol;

public abstract class OperandSymbol extends AbstractSymbol {

    @Override
    public int getPriority() {
        return 0;
    }
}
