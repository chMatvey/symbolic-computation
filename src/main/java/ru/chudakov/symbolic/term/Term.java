package ru.chudakov.symbolic.term;

public interface Term extends Comparable<Term> {

    public Number getValue();

    public void setValue(Number value);

    public int getPriority();
}
