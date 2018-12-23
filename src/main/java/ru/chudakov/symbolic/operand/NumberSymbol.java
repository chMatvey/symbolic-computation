package ru.chudakov.symbolic.operand;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForNumber;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForNumber;

public class NumberSymbol extends OperandSymbol {
    protected Double data;

    public NumberSymbol(Double data) {
        this.data = data;
    }

    public Double getData() {
        return data;
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForNumber(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForNumber(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateNumber(this);
    }
}
