package ru.chudakov.symbolic.term;

import org.jetbrains.annotations.NotNull;

public class VariableTerm extends AbstractTerm {

    private String name;

    public VariableTerm(String name) {
        this.name = name;
    }

    public VariableTerm(String name, Double value) {
        super(value);
        this.name = name;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int compareTo(@NotNull Term o) {
        if (this.getPriority() < o.getPriority())
            return -1;
        else if (this.getPriority() == this.getPriority())
            return 0;
        else
            return 1;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
