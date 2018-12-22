package ru.chudakov.symbolic.term;

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

    public void setDenominator(Double denominator) {
        this.denominator = denominator;
    }

    @Override
    public Double getValue() {
        return data / denominator;
    }
}
