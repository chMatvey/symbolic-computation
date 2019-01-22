package ru.chudakov.reader;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;

import static org.junit.Assert.*;

public class FunctionsTreeExpressionTest {

    @Test
    public void parse() {
        FunctionsTreeExpression expression = new FunctionsTreeExpression();
//        expression.getSymbol("select(a+b,1)");
//        expression.getSymbol("sin(a+e,b+c)");
//        expression.getSymbol("a+b*c");
//        expression.getSymbol("sin(a+sin(a+b))");
        expression.getSymbol("[[2,3][3,4]]");
        expression.getSymbol("a+b;c+d;e+f");
    }
}
