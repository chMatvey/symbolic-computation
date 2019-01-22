package ru.chudakov;

import ru.chudakov.reader.FunctionsTreeExpression;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.writer.SymbolXMLEncoder;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        SymbolXMLEncoder encoder = new SymbolXMLEncoder();
        FunctionsTreeExpression functionsTreeExpression = new FunctionsTreeExpression();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String expression;
        while(true) {
            System.out.println("Введите выражение");
            expression = reader.readLine();
            Symbol result = functionsTreeExpression.getSymbol(expression);
            if (result == null) {
                System.out.println("операция не поддерживается");
                continue;
            }
            System.out.println(result.toString());
            //encoder.encodeSymbolToXML(result);
            //System.out.println("Выходное выражение записано в файл");
        }
    }
}
