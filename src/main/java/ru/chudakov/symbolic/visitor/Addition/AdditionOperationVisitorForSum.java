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

public class AdditionOperationVisitorForSum implements OperationVisitor {
    private SumSymbol firstArgument;

    public AdditionOperationVisitorForSum(SumSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.add(secondArgument);
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.add(secondArgument);
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.add(secondArgument);
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.addAll(secondArgument.getBranches());
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.add(secondArgument);
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.add(secondArgument);
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(firstArgument.getBranches());
        list.add(secondArgument);
        return new SumSymbol(list);
    }
}
