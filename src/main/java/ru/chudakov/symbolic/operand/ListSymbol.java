package ru.chudakov.symbolic.operand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListSymbol extends OperandSymbol {
    private List<Symbol> list;

    @Override
    public Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values) {
        List<Symbol> list = new ArrayList<>();
        for (Symbol symbol : this.list) {
            list.add(symbol.putValue(values));
        }
        return new ListSymbol(list);
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return null;
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return null;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
