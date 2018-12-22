package ru.chudakov.symbolic.visitor.Addition;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.FunctionSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.PowerSymbol;
import ru.chudakov.symbolic.operation.SumSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;

public class AdditionOperationVisitorForSum implements OperationVisitor {
    private SumSymbol firstArgument;

    public AdditionOperationVisitorForSum(SumSymbol firstArgument) {
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
        for (int i = 0; i < array.length; i++) {
            firstArgument.addBranch(array[i]);
        }
        return firstArgument;
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        firstArgument.addBranch(secondArgument);
        return firstArgument;
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        firstArgument.addBranch(secondArgument);
        return firstArgument;
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        firstArgument.addBranch(secondArgument);
        return firstArgument;
    }
}
