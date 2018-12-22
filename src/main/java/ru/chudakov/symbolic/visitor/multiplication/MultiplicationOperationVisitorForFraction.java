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

public class MultiplicationOperationVisitorForFraction implements OperationVisitor {
    private FractionSymbol firstArgument;

    public MultiplicationOperationVisitorForFraction(FractionSymbol firstArgument){
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return new FractionSymbol(
                firstArgument.getNumerator() * secondArgument.getData(),
                firstArgument.getDenominator()
        );
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        return new FractionSymbol(
                firstArgument.getNumerator() * secondArgument.getNumerator(),
                firstArgument.getDenominator() * secondArgument.getDenominator()
        );
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        Symbol[] array = secondArgument.toArray();
        List<Symbol> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(new MulSymbol(firstArgument, array[i]));
        }
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        secondArgument.add(firstArgument);
        return secondArgument;
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }
}
