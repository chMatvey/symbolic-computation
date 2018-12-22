package ru.chudakov.symbolic.operand;

import ru.chudakov.symbolic.Symbol;

public class VariableSymbol extends OperandSymbol {
    private String name;

    public VariableSymbol(String name, Double value) {
        super(value);
        this.name = name;
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            VariableSymbol variable = (VariableSymbol) o;
            result = name.compareTo(variable.name);
        }
        return result;
    }
}
