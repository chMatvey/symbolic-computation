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

public class MultiplicationOperationVisitorForPower implements OperationVisitor {
    private PowerSymbol firstArgument;

    public MultiplicationOperationVisitorForPower(PowerSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        if (firstArgument.getBase().compareTo(secondArgument) == 0) {
            return new PowerSymbol(
                    firstArgument.getBase(),
                    firstArgument.getIndex().add(new NumberSymbol(1d))
            );
        } else {
            return new MulSymbol(firstArgument, secondArgument);
        }
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
        return secondArgument.mul(firstArgument);
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        if (firstArgument.getBase().compareTo(secondArgument.getBase()) == 0) {
            return new PowerSymbol(
                    firstArgument.getBase(),
                    firstArgument.getIndex().add(secondArgument.getIndex())
            );
        } else {
            return new MulSymbol(firstArgument, secondArgument);
        }
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return new MulSymbol(firstArgument, secondArgument);
    }
}
