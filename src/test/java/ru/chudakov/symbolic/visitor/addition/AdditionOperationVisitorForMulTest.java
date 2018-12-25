package ru.chudakov.symbolic.visitor.addition;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.PowerSymbol;
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
    public void calculatePower() {
        PowerSymbol powerSymbol1 = new PowerSymbol(new VariableSymbol("a"), new NumberSymbol(2d));
        PowerSymbol powerSymbol2 = new PowerSymbol(new VariableSymbol("b"), new NumberSymbol(3d));
        Symbol result = powerSymbol1.add(powerSymbol2);
        result = powerSymbol1.add(powerSymbol1);
        Symbol excepted = new MulSymbol(
                new PowerSymbol(new VariableSymbol("b"), new NumberSymbol(3d)),
                new NumberSymbol(2d)
        );
        assertEquals(result, excepted);
        MulSymbol mulSymbol1 = new MulSymbol(powerSymbol1, new NumberSymbol(3d));
        MulSymbol mulSymbol2 = new MulSymbol(powerSymbol2, new NumberSymbol(2d));
        result = mulSymbol1.add(mulSymbol2);
        result = mulSymbol1.add(mulSymbol1);
        excepted = new MulSymbol(powerSymbol1, new NumberSymbol(6d));
        assertEquals(result, excepted);
    }

    @Test
    public void calculateMul() {
        VariableSymbol variableSymbol = new VariableSymbol("a");
        MulSymbol mulSymbol = new MulSymbol(variableSymbol, new NumberSymbol(3d));
        Symbol result = variableSymbol.add(mulSymbol);
    }
}
