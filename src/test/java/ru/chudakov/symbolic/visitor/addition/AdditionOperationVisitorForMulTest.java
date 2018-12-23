package ru.chudakov.symbolic.visitor.addition;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.SumSymbol;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdditionOperationVisitorForMulTest {

    @Test
    public void calculateSum() {
        ArrayList<Symbol> list = new ArrayList<>();
        list.add(new NumberSymbol(3d));
        list.add(new VariableSymbol("a"));
        list.add(new VariableSymbol("b"));
        MulSymbol mulSymbol = new MulSymbol(list);
        Symbol result = mulSymbol.add(mulSymbol);
        list.remove(new NumberSymbol(3d));
        list.add(new NumberSymbol(6d));
        Symbol expected = new MulSymbol(list);
        assertTrue(result.compareTo(expected) == 0);
        assertEquals(((MulSymbol)result).getCoefficient().getData(), 6d, 0d );
    }

    @Test
    public void calculateMul() {
    }
}
