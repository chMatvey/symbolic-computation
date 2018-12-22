package ru.chudakov.symbolic.visitor.term;

import ru.chudakov.symbolic.term.FractionTerm;
import ru.chudakov.symbolic.term.NumberTerm;
import ru.chudakov.symbolic.visitor.term.TermVisitor;

public class FractionTermVisitor implements TermVisitor {
    private FractionTerm firstTerm;

    public FractionTermVisitor(FractionTerm firstTerm) {
        this.firstTerm = firstTerm;
    }

    @Override
    public NumberTerm addNumber(NumberTerm secondTerm) {
        return new FractionTerm(
                firstTerm.getNumerator() + secondTerm.getValue() * firstTerm.getDenominator(),
                firstTerm.getDenominator()
        );
    }

    @Override
    public NumberTerm addFraction(FractionTerm secondTerm) {
        Double nok = nok(firstTerm.getDenominator(), secondTerm.getDenominator());
        return new FractionTerm(
                firstTerm.getNumerator() * nok / firstTerm.getDenominator()
                        + secondTerm.getNumerator() * nok / secondTerm.getDenominator(),
                nok
        );
    }

    private Double nod(Double a, Double b) {
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

    private Double nok(Double a, Double b) {
        return a * b / nod(a, b);
    }
}
