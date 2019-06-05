package ru.chudakov.symbolic.cache;

import org.junit.Test;
import ru.chudakov.reader.FunctionsTreeExpression;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.writer.SymbolXMLEncoder;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.*;

public class CacheSymbolSingletonTest {
    @Test
    public void createAndCalculateFunction() throws JAXBException {
        SymbolXMLEncoder encoder = new SymbolXMLEncoder("result.xml");
        FunctionsTreeExpression functionsTreeExpression = new FunctionsTreeExpression();
        String expression = "f(x_,y_)=x+y";
        Symbol result = functionsTreeExpression.getSymbol(expression);
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        expression = "f(2,3)";
        result = functionsTreeExpression.getSymbol(expression);
        expression = "f(2,b)";
        result = functionsTreeExpression.getSymbol(expression);
    }
}
