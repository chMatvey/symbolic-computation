package ru.chudakov.symbolic;

import ru.chudakov.symbolic.term.Term;

public class VariableSymbol extends OperandSymbol {
    private String name;

    public VariableSymbol(String name) {
        super(1d);
        this.name = name;
    }

    public VariableSymbol(String name, Double value) {
        super(value);
        this.name = name;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            result = name.compareTo(((VariableSymbol) o).name);
        }
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
