package ru.chudakov.symbolic.visitor.term;

import ru.chudakov.symbolic.term.FractionTerm;
import ru.chudakov.symbolic.term.NumberTerm;

public interface TermVisitor {
    public NumberTerm addNumber(NumberTerm secondTerm);

    public NumberTerm addFraction(FractionTerm secondTerm);
}
