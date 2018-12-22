package ru.chudakov.symbolic.operation;

import ru.chudakov.symbolic.Symbol;

import java.util.Collection;

public class SumSymbol extends ArithmeticOperationSymbol {

    public SumSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public SumSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }
}
