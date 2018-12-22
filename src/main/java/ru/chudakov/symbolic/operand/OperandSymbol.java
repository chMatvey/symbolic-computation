package ru.chudakov.symbolic.operand;

import ru.chudakov.symbolic.AbstractSymbol;

public abstract class OperandSymbol extends AbstractSymbol {
    protected Double data;

    public OperandSymbol(Double data) {
        this.data = data;
    }

    @Override
    public int getPriority() {
        return 0;
    }


}
