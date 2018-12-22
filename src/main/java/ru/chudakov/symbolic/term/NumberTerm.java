package ru.chudakov.symbolic.term;

import org.jetbrains.annotations.NotNull;

public class NumberTerm extends AbstractTerm {

    public NumberTerm(Double data) {
        super(data);
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
