package ru.chudakov.symbolic.operand;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.Addition.AdditionOperationVisitorForNumber;
import ru.chudakov.symbolic.visitor.OperationVisitor;

public class NumberSymbol extends OperandSymbol {
    public NumberSymbol(Double data) {
        super(data);
    }

    public Double getData() {
        return data;
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForNumber(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateNumber(this);
    }
}
