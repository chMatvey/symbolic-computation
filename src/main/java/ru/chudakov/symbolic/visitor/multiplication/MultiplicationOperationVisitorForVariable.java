package ru.chudakov.symbolic.visitor.multiplication;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.FunctionSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.PowerSymbol;
import ru.chudakov.symbolic.operation.SumSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationOperationVisitorForVariable implements OperationVisitor {
    private VariableSymbol firstArgument;

    public MultiplicationOperationVisitorForVariable(VariableSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        if (firstArgument.compareTo(secondArgument) == 0) {
            return new PowerSymbol(firstArgument, new NumberSymbol(2d));
        } else {
            return new MulSymbol(firstArgument, secondArgument);
        }
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        Symbol[] array = secondArgument.toArray();
        List<Symbol> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(firstArgument.mul(array[i]));
        }
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        secondArgument.addBranch(firstArgument);
        return secondArgument;
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        if (secondArgument.getBase().compareTo(firstArgument) == 0) {
            secondArgument.incrementIndex();
            return secondArgument;
        } else {
            return new MulSymbol(firstArgument, secondArgument);
        }
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }
}
