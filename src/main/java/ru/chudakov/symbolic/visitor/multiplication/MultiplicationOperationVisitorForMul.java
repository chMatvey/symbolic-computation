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

    private Symbol calculateSymbol(Symbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.add(secondArgument);
        return new MulSymbol(list);
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        Symbol[] array = secondArgument.toArray();
        List<Symbol> list = new ArrayList<>();
        for (Symbol symbol : array) {
            list.add(firstArgument.mul(symbol));
        }
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.addAll(secondArgument.getBranches());
        return new MulSymbol(list);
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        Symbol[] array = firstArgument.toArray();
        List<Symbol> list = new ArrayList<>();
        for (Symbol symbol : array) {
            if (secondArgument.getBase().compareTo(symbol) == 0) {
                list.add(symbol.mul(secondArgument));
            } else {
                list.add(symbol);
            }
        }
        return new MulSymbol(list);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return calculateSymbol(secondArgument);
    }
}
