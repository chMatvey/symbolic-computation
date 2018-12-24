package ru.chudakov.symbolic.operation;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OperationSymbolTest {

    @Test
    public void createSumSymbol() {
        List<Symbol> list = new ArrayList<>();
        list.add(new NumberSymbol(1d));
        list.add(new FractionSymbol(2d, 3d));
        list.add(new FractionSymbol(4d, 6d));
        list.add(new VariableSymbol("a"));
        list.add(new VariableSymbol("a"));
        list.add(new MulSymbol(new VariableSymbol("a"), new NumberSymbol(3d)));
        Symbol sum = new SumSymbol(list);
        return;
    }
}
