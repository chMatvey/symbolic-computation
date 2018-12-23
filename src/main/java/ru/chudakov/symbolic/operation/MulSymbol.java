package ru.chudakov.symbolic.operation;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForMul;

import java.util.Collection;

public class MulSymbol extends ArithmeticOperationSymbol {

    public MulSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public MulSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }

    public NumberSymbol getCoefficient() {
        NumberSymbol coefficient = new NumberSymbol(1d);
        if (branches.contains(coefficient)) {
            return (NumberSymbol) branches.ceiling(coefficient);
        } else {
            return coefficient;
        }
    }

    public void setCoefficient(NumberSymbol coefficient) {
        branches.remove(coefficient);
        branches.add(coefficient);
    }

    @Override
    protected Symbol calculate(Symbol firstArgument, Symbol secondArgument) {
        return firstArgument.mul(secondArgument);
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForMul(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return super.mul(secondArgument);
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateMul(this);
    }
}
