package ru.chudakov.symbolic;

import org.junit.Test;
import ru.chudakov.reader.FunctionsTreeExpression;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;

import java.util.TreeMap;
import java.util.TreeSet;

public class SymbolTest {

    @Test
    public void putValue() {
        FunctionsTreeExpression functionsTreeExpression = new FunctionsTreeExpression();
        String expression = "x+2";

        Symbol symbol = functionsTreeExpression.getSymbol(expression);

        TreeSet<VariableSymbol> set = functionsTreeExpression.getVariables(expression);
        TreeMap<VariableSymbol, NumberSymbol> treeMap = new TreeMap<>();
        treeMap.put(set.first(), new NumberSymbol(3d));

        Symbol numberSymbol = symbol.putValue(treeMap);
        System.out.println(numberSymbol);
    }
}
