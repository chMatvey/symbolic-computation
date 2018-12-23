package ru.chudakov.symbolic.visitor.addition;

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

public class AdditionOperationVisitorForFraction implements OperationVisitor {
    private FractionSymbol firstArgument;

    public AdditionOperationVisitorForFraction(FractionSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    @Override
    public Symbol calculateNumber(NumberSymbol secondArgument) {
        return new FractionSymbol(
                secondArgument.getData() * firstArgument.getDenominator() + firstArgument.getNumerator(),
                firstArgument.getDenominator()
        );
    }

    @Override
    public Symbol calculateFraction(FractionSymbol secondArgument) {
        Double nok = nok(firstArgument.getDenominator(), secondArgument.getDenominator());
        return new FractionSymbol(
                firstArgument.getNumerator() * nok / firstArgument.getDenominator()
                        + secondArgument.getNumerator() * nok / secondArgument.getDenominator(),
                nok
        );
    }

    @Override
    public Symbol calculateVariable(VariableSymbol secondArgument) {
        return new SumSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateSum(SumSymbol secondArgument) {
        secondArgument.addBranch(firstArgument);
        return secondArgument;
    }

    @Override
    public Symbol calculateMul(MulSymbol secondArgument) {
        secondArgument.addBranch(firstArgument);
        return secondArgument;
    }

    @Override
    public Symbol calculatePower(PowerSymbol secondArgument) {
        return new SumSymbol(firstArgument, secondArgument);
    }

    @Override
    public Symbol calculateFunction(FunctionSymbol secondArgument) {
        return new SumSymbol(firstArgument, secondArgument);
    }

    private Double nod(@NotNull Double a, Double b) {
        if (a.equals(b))
            return a;
        Double result;
        Double delta = a - b;
        if (delta < 0) {
            delta *= -1;
            result = nod(a, delta);
        } else {
            result = nod(b, delta);
        }
        return result;
    }

    @NotNull
    private Double nok(Double a, Double b) {
        return a * b / nod(a, b);
    }
}
