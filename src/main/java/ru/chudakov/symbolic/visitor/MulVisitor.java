package ru.chudakov.symbolic.visitor;

import ru.chudakov.symbolic.*;

import java.util.Map;
import java.util.TreeMap;

public class MulVisitor implements OperationVisitor {
    @Override
    public Symbol add(Symbol symbol, AbstractSymbol abstractSymbol) throws ClassCastException {
        Mul mul = (Mul) symbol;
        return new Sum(mul.getBranches(), abstractSymbol.getBranches());
    }

    @Override
    public Symbol add(Symbol symbol, Map.Entry<Symbol, Symbol> entry) throws ClassCastException {
        Mul mul = (Mul) symbol;
        TreeMap<Symbol, Symbol> treeMap = new TreeMap<>();
        treeMap.put(entry.getKey(), entry.getValue());
        return new Sum(mul.getBranches(), treeMap);
    }
}
