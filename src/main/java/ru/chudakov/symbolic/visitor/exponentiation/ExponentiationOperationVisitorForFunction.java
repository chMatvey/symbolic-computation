package ru.chudakov.symbolic.visitor.exponentiation;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.FunctionSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.PowerSymbol;
import ru.chudakov.symbolic.operation.SumSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;

public class ExponentiationOperationVisitorForFunction implements OperationVisitor {
    private FunctionSymbol firstArgument;

    public ExponentiationOperationVisitorForFunction(FunctionSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        if (secondArgument.getData().equals(0d)) {
            return new NumberSymbol(1d);
        }
        return new PowerSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        if (secondArgument.getData().equals(0d)) {
            return new NumberSymbol(1d);
        }
        return new PowerSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        return new PowerSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        return new PowerSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        return new PowerSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        return new PowerSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return new PowerSymbol(firstArgument, secondArgument);
    }
}
