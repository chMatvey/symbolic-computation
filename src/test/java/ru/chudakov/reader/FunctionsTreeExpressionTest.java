package ru.chudakov.reader;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;

import static org.junit.Assert.*;

public class FunctionsTreeExpressionTest {

    @Test
    public void parse() {
        FunctionsTreeExpression expression = new FunctionsTreeExpression();
        expression.parseExpression("select(a+b,1)");
        expression.parseExpression("sin(a+e,b+c)");
        expression.parseExpression("a+b*c");
        expression.parseExpression("last(select(f,1))^2-4*last(select(f,0))*select(f,2))");
    }
}
