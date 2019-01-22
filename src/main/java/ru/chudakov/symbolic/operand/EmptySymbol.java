package ru.chudakov.symbolic.operand;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;

import java.util.TreeMap;

public class EmptySymbol extends OperandSymbol {
    @Override
    public Symbol add(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return null;
    }

    @Override
    public Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values) {
        return null;
    }
}
