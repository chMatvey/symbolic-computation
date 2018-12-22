package ru.chudakov.symbolic.operation;

import ru.chudakov.symbolic.Symbol;

import java.util.Collection;

public class MulSymbol extends ArithmeticOperationSymbol {

    public MulSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public MulSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }
}
