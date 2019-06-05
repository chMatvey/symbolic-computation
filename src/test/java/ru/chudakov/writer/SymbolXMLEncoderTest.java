package ru.chudakov.writer;

import org.junit.Test;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.PowerSymbol;
import ru.chudakov.symbolic.operation.SumSymbol;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

import static org.junit.Assert.*;

public class SymbolXMLEncoderTest {

    @Test
    public void encodeSymbolToXML() throws JAXBException {
        Symbol symbol = new MulSymbol(new VariableSymbol("a"), new VariableSymbol("b"));
        SymbolXMLEncoder encoder = new SymbolXMLEncoder("result.xml");
        encoder.encodeSymbolToXML(symbol);
    }
}
