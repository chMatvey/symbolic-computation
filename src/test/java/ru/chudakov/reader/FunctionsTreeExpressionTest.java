package ru.chudakov.reader;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;

import static org.junit.Assert.*;

public class FunctionsTreeExpressionTest {

    @Test
    public void parse() {
        FunctionsTreeExpression expression = new FunctionsTreeExpression();
        Symbol result = expression.getSymbol("5+7+8+a+b");
    }
}
