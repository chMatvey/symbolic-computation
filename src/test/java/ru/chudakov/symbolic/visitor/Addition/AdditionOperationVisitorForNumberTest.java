package ru.chudakov.symbolic.visitor.addition;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;

import static org.junit.Assert.*;

public class AdditionOperationVisitorForNumberTest {
    @Test
    public void calculateNumberTest() {
        NumberSymbol numberSymbol = new NumberSymbol(2d);
        Symbol result = numberSymbol.add(new NumberSymbol(3d));
        assertEquals(5d, ((NumberSymbol) result).getData(), 0d);
    }

    @Test
    public void calculateFractionTest() {
        NumberSymbol numberSymbol = new NumberSymbol(2d);
        Symbol result = numberSymbol.add(new FractionSymbol(3d, 2d));
        FractionSymbol fractionSymbol = (FractionSymbol) result;
        assertEquals(7, fractionSymbol.getNumerator(), 0d);
        assertEquals(2, fractionSymbol.getDenominator(), 0d);
    }
}
