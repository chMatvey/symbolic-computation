package ru.chudakov;

import ru.chudakov.reader.FunctionsTreeExpression;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.writer.SymbolXMLEncoder;

import javax.xml.bind.JAXBException;

public class Main {
    public static void main(String[] args) throws JAXBException {
        SymbolXMLEncoder encoder = new SymbolXMLEncoder();
        FunctionsTreeExpression expression = new FunctionsTreeExpression();
        Symbol result = expression.getSymbol("5+7+8+a+b+a*b");
        encoder.encodeSymbolToXML(result);
    }
}
