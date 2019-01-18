package ru.chudakov.symbolic.operand;

import lombok.NoArgsConstructor;
import ru.chudakov.symbolic.AbstractSymbol;

@NoArgsConstructor
public abstract class OperandSymbol extends AbstractSymbol {

    @Override
    public int getPriority() {
        return 0;
    }
}
