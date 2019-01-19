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

import java.util.ArrayList;
import java.util.List;

public class ExponentiationOperationVisitorForMul implements OperationVisitor {
    private MulSymbol firstArgument;

    public ExponentiationOperationVisitorForMul(MulSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    private Symbol calculateSymbol(Symbol secondArgument){
        List<Symbol> list = new ArrayList<>();
        for (Symbol symbol : firstArgument.getBranches()) {
            list.add(symbol.pow(secondArgument));
        }
        return new MulSymbol(list);
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        if (secondArgument.getData().equals(0d)) {
            return new NumberSymbol(1d);
        }
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        if (secondArgument.getData().equals(0d)) {
            return new NumberSymbol(1d);
        }
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }
}
