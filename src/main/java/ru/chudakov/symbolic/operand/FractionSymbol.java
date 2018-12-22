package ru.chudakov.symbolic.operand;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.Addition.AdditionOperationVisitorForFraction;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForFraction;

public class FractionSymbol extends NumberSymbol {
    private Double denominator;

    public FractionSymbol(Double numerator, Double denominator) {
        super(numerator);
        this.denominator = denominator;
    }

    public Double getNumerator() {
        return data;
    }

    public Double getDenominator() {
        return denominator;
    }

    @Override
    public Double getData() {
        return data / denominator;
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForFraction(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForFraction(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateFraction(this);
    }
}
