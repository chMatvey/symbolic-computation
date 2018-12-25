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

    public void encodeSymbolToXML(Symbol symbol) throws JAXBException {
        String path = new File("").getAbsolutePath();
        String fileName = "result.xml";
        File file = new File(path, fileName);
        encodeSymbolToXML(file, symbol);
    }

    public void encodeSymbolToXML(File file, Symbol symbol) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(
                NumberSymbol.class,
                FractionSymbol.class,
                VariableSymbol.class,
                SumSymbol.class,
                MulSymbol.class,
                PowerSymbol.class,
                FunctionSymbol.class
        );
        Marshaller marshaller = context.createMarshaller();
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(symbol, file);
    }
}
