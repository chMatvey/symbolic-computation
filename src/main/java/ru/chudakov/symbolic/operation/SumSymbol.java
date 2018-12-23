package ru.chudakov.symbolic.operation;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForSum;
import ru.chudakov.symbolic.visitor.OperationVisitor;

import java.util.Collection;

public class SumSymbol extends ArithmeticOperationSymbol {

    public SumSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public SumSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }

    @Override
    protected Symbol calculate(Symbol firstArgument, Symbol secondArgument) {
        return firstArgument.add(secondArgument);
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForSum(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return super.mul(secondArgument);
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateSum(this);
    }
}
