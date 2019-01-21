package ru.chudakov.symbolic.operand;

import lombok.*;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForNumber;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.exponentiation.ExponentiationOperationVisitorForNumber;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForNumber;

import javax.xml.bind.annotation.*;
import java.util.TreeMap;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "number")
public class NumberSymbol extends OperandSymbol {
    protected Double data;

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForNumber(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForNumber(this));
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return secondArgument.callVisitor(new ExponentiationOperationVisitorForNumber(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateNumber(this);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values) {
        return this;
    }
}
