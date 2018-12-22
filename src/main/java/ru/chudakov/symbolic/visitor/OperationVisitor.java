package ru.chudakov.symbolic.visitor;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.FunctionSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.PowerSymbol;
import ru.chudakov.symbolic.operation.SumSymbol;

public interface OperationVisitor {
    public Symbol calculateNumber(NumberSymbol secondArgument);

    public Symbol calculateFraction(FractionSymbol secondArgument);

    public Symbol calculateVariable(VariableSymbol secondArgument);

    public Symbol calculateSum(SumSymbol secondArgument);

    public Symbol calculateMul(MulSymbol secondArgument);

    public Symbol calculatePower(PowerSymbol secondArgument);

    public Symbol calculateFunction(FunctionSymbol secondArgument);
}
