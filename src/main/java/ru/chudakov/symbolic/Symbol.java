package ru.chudakov.symbolic;

import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;

import java.util.TreeMap;

public interface Symbol extends Comparable<Symbol> {
    public int getPriority();

    public Symbol add(Symbol secondArgument);

    public Symbol mul(Symbol secondArgument);

    public Symbol pow(Symbol secondArgument);

    public Symbol callVisitor(OperationVisitor visitor);

    public Symbol getFirst();

    public Symbol getLast();

    public int length();

    public Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values);
}
