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

public class MultiplicationOperationVisitorForPower implements OperationVisitor {
    private PowerSymbol firstArgument;

    public MultiplicationOperationVisitorForPower(PowerSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return null;
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        return null;
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        return null;
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        return null;
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        return null;
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
