package ru.chudakov.symbolic.visitor.multiplication;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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

public class MultiplicationOperationVisitorForSum implements OperationVisitor {
    private SumSymbol firstArgument;

    public MultiplicationOperationVisitorForSum(SumSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @NotNull
    @Contract("_ -> new")
    private Symbol calculateAll(Symbol secondArgument) {
        Symbol[] array = firstArgument.toArray();
        List<Symbol> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(secondArgument.mul(array[i]));
        }
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return calculateAll(secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        return calculateAll(secondArgument);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        return calculateAll(secondArgument);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        Symbol[] firstArray = firstArgument.toArray();
        Symbol[] secondArray = secondArgument.toArray();
        List<Symbol> list = new ArrayList<>();
        for (Symbol symbol : firstArray) {
            for (Symbol value : secondArray) {
                list.add(symbol.mul(value));
            }
        }
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        return calculateAll(secondArgument);
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        return calculateAll(secondArgument);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return calculateAll(secondArgument);
    }
}
