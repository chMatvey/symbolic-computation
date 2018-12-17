package ru.chudakov.symbolic.visitor;

import ru.chudakov.symbolic.AbstractSymbol;
import ru.chudakov.symbolic.Symbol;

import java.util.Map;

public interface OperationVisitor {
    Symbol add(Symbol symbol, AbstractSymbol abstractSymbol);

    Symbol add(Symbol symbol, Map.Entry<Symbol, Symbol> entry);
}
