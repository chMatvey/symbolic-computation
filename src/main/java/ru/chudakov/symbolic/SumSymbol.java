package ru.chudakov.symbolic;

import ru.chudakov.symbolic.term.NumberTerm;

import java.util.Collection;

public class SumSymbol extends OperationSymbol {
    public SumSymbol(Collection<Symbol> nodes, Collection<NumberTerm> coefficients) {
        super(nodes, coefficients);
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }
}
