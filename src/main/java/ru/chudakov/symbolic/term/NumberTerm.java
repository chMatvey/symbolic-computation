package ru.chudakov.symbolic.term;

import org.jetbrains.annotations.NotNull;

public class NumberTerm implements Term {

    private Number data;

    public NumberTerm(Number data) {
        this.data = data;
    }

    @Override
    public Number getValue() {
        return data;
    }

    @Override
    public void setValue(Number value) {
        data = value;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int compareTo(@NotNull Term o) {
        if (this.getPriority() < getPriority())
            return -1;
        else if (this.getPriority() == this.getPriority()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
