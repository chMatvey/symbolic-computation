package ru.chudakov.symbolic.term;

import org.jetbrains.annotations.NotNull;

public class VariableTerm implements Term {

    private String name;
    private Number value;

    public VariableTerm(String name) {
        this.name = name;
    }

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public void setValue(Number value) {
        this.value = value;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int compareTo(@NotNull Term o) {
        if (this.getPriority() < o.getPriority())
            return -1;
        else if (this.getPriority() == o.getPriority())
            return name.compareTo(((VariableTerm) o).name);
        else
            return 1;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
