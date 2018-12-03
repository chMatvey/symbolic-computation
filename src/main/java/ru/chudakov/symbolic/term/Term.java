package ru.chudakov.symbolic.term;

public interface Term<T extends Number> extends Comparable<Term> {
    public TermTypes getType();

    public String getExpressionName();
}
