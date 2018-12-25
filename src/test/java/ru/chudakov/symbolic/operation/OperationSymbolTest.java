package ru.chudakov.symbolic.operation;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.writer.SymbolXMLEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OperationSymbolTest {

    @Test
    public void createSumSymbol() {
        List<Symbol> list = new ArrayList<>();
        list.add(new NumberSymbol(1d));
        list.add(new FractionSymbol(2d, 3d));
        list.add(new VariableSymbol("a"));
        list.add(new VariableSymbol("a"));
        list.add(new MulSymbol(new VariableSymbol("a"), new NumberSymbol(3d)));
        Symbol result = new SumSymbol(list);;
    }

    @Test
    public void testOperation() {
        Symbol result = new VariableSymbol("a");
        result = result.mul(new VariableSymbol("b"));
        result = result.add(new VariableSymbol("a"));
    }
}
