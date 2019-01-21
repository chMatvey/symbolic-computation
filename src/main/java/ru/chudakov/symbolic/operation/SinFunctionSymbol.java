package ru.chudakov.symbolic.operation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeMap;

@NoArgsConstructor
@XmlRootElement(name = "sin")
public class SinFunctionSymbol extends FunctionSymbol {

    public SinFunctionSymbol(Symbol argument) {
        this.argument = argument;
    }

    @Override
    public Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values) {
        Symbol symbol = argument.putValue(values);
        if (argument.getPriority() == 0) {
            return new NumberSymbol(
                    Math.sin(((NumberSymbol) symbol).getData())
            );
        } else {
            return new SinFunctionSymbol(symbol);
        }
    }

    @Override
    public String toString() {
        return "sin(" + argument.toString() + ")";
    }
}
