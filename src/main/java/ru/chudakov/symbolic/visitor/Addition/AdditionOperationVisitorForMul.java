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
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AdditionOperationVisitorForMul implements OperationVisitor {
    private MulSymbol firstArgument;

    public AdditionOperationVisitorForMul(MulSymbol firstArgument) {
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
        return new AdditionOperationVisitorForVariable(secondArgument).calculateMul(firstArgument);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        List<Symbol> list = new ArrayList<>(secondArgument.getBranches());
        list.add(firstArgument);
        return new SumSymbol(list);
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        if (firstArgument.compareTo(secondArgument) == 0) {
            Set<Symbol> set = new TreeSet<>(firstArgument.getBranches());
            set.remove(firstArgument.getCoefficient());
            set.add(firstArgument.getCoefficient().add(secondArgument.getCoefficient()));
            return new MulSymbol(set);
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
