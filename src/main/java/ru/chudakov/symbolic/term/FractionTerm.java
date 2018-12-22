package ru.chudakov.symbolic.term;

import ru.chudakov.symbolic.visitor.term.FractionTermVisitor;
import ru.chudakov.symbolic.visitor.term.TermVisitor;

public class FractionTerm extends NumberTerm {
    private Double denominator;

    public FractionTerm(Double data) {
        super(data);
        denominator = 1d;
    }

    public FractionTerm(Double numerator, Double denominator) {
        super(numerator);
        if (denominator == 0d)
            throw new ArithmeticException();
        this.denominator = denominator;
    }

    public Double getDenominator() {
        return denominator;
    }

    public Double getNumerator() {
        return data;
    }

    @Override
    public NumberTerm add(NumberTerm secondTerm) {
        return secondTerm.callVisitor(new FractionTermVisitor(this));
    }

    @Override
    protected NumberTerm callVisitor(TermVisitor visitor) {
        this.visitor = visitor;
        return visitor.addFraction(this);
    }

    @Override
    public Double getValue() {
        return data / denominator;
    }

    @Override
    public String toString() {
        return data.toString() + "/" + denominator.toString();
    }
}
