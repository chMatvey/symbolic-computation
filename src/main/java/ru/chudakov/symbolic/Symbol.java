package ru.chudakov.symbolic;

import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;

import java.util.TreeMap;

public interface Symbol extends Comparable<Symbol> {
    int getPriority();

    Symbol add(Symbol secondArgument);

    Symbol mul(Symbol secondArgument);

    Symbol pow(Symbol secondArgument);

    Symbol callVisitor(OperationVisitor visitor);

    Symbol getFirst();

    Symbol getLast();

    int length();

    Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values);
}
