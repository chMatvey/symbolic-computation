package ru.chudakov.symbolic.term;

public interface Term extends Comparable<Term> {

    public Double getValue();

    public int getPriority();

    public Term add(Term term);
}
