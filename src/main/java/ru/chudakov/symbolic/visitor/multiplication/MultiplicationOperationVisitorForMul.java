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

public class MultiplicationOperationVisitorForMul implements OperationVisitor {
    private MulSymbol firstArgument;

    public MultiplicationOperationVisitorForMul(MulSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        firstArgument.addBranch(secondArgument);
        return firstArgument;
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        firstArgument.addBranch(secondArgument);
        return firstArgument;
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        firstArgument.addBranch(secondArgument);
        return firstArgument;
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
        if (firstArgument.compareTo(secondArgument) == 0) {
            return new PowerSymbol(firstArgument, new NumberSymbol(2d));
        } else {
            return null;
        }
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        return null;
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return null;
    }
}
