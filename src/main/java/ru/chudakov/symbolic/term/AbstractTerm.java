package ru.chudakov.symbolic.term;

public abstract class AbstractTerm implements Term {

    protected Double data;

    public AbstractTerm(Double data) {
        this.data = data;
    }

    @Override
    public Double getValue() {
        return data;
    }

    @Override
    public int compareTo(Term o) {
        if (this.getPriority() < o.getPriority())
            return -1;
        else if (this.getPriority() == this.getPriority())
            return 0;
        else
            return 1;
    }
}
