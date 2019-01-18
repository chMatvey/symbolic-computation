package ru.chudakov.symbolic;

import ru.chudakov.symbolic.visitor.OperationVisitor;

public interface Symbol extends Comparable<Symbol> {
    public int getPriority();

    public Symbol add(Symbol secondArgument);

    public Symbol mul(Symbol secondArgument);

    public Symbol pow(Symbol secondArgument);

    public Symbol callVisitor(OperationVisitor visitor);

    public Symbol getFirst();

    public Symbol getLast();

    public int length();
}
