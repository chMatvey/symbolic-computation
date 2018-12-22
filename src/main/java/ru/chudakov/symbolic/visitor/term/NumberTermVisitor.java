package ru.chudakov.symbolic.visitor.term;

import ru.chudakov.symbolic.term.FractionTerm;
import ru.chudakov.symbolic.term.NumberTerm;
import ru.chudakov.symbolic.visitor.term.TermVisitor;

public class NumberTermVisitor implements TermVisitor {
    private NumberTerm firstTerm;

    public NumberTermVisitor(NumberTerm firstTerm) {
        this.firstTerm = firstTerm;
    }

    @Override
    public NumberTerm addNumber(NumberTerm secondTerm) {
        return new NumberTerm(firstTerm.getValue() + secondTerm.getValue());
    }

    @Override
    public NumberTerm addFraction(FractionTerm secondTerm) {
        return new FractionTerm(
                firstTerm.getValue() * secondTerm.getDenominator() + secondTerm.getNumerator(),
                secondTerm.getDenominator()
        );
    }
}
