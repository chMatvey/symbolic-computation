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

    public void incrementCoefficient() {
        NumberSymbol number = new NumberSymbol(1d);
        if (branches.contains(number)) {
            number = (NumberSymbol) number.add(branches.ceiling(number));
            branches.remove(number);
            branches.add(number);
        } else {
            branches.add(number.add(number));
        }
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

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result != 0 && branches.size() == 2) {
            result = branches.first().compareTo(o);
        }
        return result;
    }
}
