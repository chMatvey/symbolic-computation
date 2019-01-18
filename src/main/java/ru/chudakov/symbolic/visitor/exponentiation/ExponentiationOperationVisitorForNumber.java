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

public class ExponentiationOperationVisitorForNumber implements OperationVisitor {
    private NumberSymbol firstArgument;

    public ExponentiationOperationVisitorForNumber(NumberSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return new PowerSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
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
