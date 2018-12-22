package ru.chudakov.symbolic.term;

public abstract class AbstractTerm implements Term {

    protected Double data;

    public AbstractTerm(){

    }

    public AbstractTerm(Double data) {
        this.data = data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    @Override
    public Double getValue() {
        return data;
    }
}
