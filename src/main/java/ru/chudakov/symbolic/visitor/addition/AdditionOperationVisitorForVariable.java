package ru.chudakov.symbolic.visitor.addition;

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

public class AdditionOperationVisitorForVariable implements OperationVisitor {
    private VariableSymbol firstArgument;

    public AdditionOperationVisitorForVariable(VariableSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return new SumSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        return new SumSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        if (firstArgument.compareTo(secondArgument) == 0) {
            return new MulSymbol(firstArgument, new NumberSymbol(2d));
        } else {
            return new SumSymbol(firstArgument, secondArgument);
        }
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(secondArgument.getBranches());
        list.add(firstArgument);
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        if (secondArgument.compareTo(firstArgument) == 0) {
            List<Symbol> list = new ArrayList<>(secondArgument.getBranches());
            list.add(new NumberSymbol(1d));
            SumSymbol symbol = new SumSymbol(list);
            list = new ArrayList<>(symbol.getBranches());
            return new MulSymbol(list);
        } else {
            return new SumSymbol(firstArgument, secondArgument);
        }
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        return new SumSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return new SumSymbol(firstArgument, secondArgument);
    }
}
