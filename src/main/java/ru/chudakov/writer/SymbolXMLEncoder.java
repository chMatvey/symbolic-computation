package ru.chudakov.writer;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class SymbolXMLEncoder {

    private Marshaller marshaller;
    private File file;

    public SymbolXMLEncoder(String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(
                NumberSymbol.class,
                FractionSymbol.class,
                VariableSymbol.class,
                SumSymbol.class,
                MulSymbol.class,
                PowerSymbol.class,
                FunctionSymbol.class
        );

        marshaller = context.createMarshaller();

        String path = new File("").getAbsolutePath();
        file = new File(path, fileName);
    }

    public void encodeSymbolToXML(Symbol symbol) throws JAXBException {
        marshaller.marshal(symbol, file);
    }
}
