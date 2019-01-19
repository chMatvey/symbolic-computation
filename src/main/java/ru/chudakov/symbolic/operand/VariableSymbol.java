package ru.chudakov.symbolic.operand;

import lombok.*;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForVariable;
import ru.chudakov.symbolic.visitor.exponentiation.ExponentiationOperationVisitorForVariable;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForVariable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "variable")
public class VariableSymbol extends OperandSymbol {
    private String name;

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            VariableSymbol variable = (VariableSymbol) o;
            result = name.compareTo(variable.name);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = super.equals(obj);
        if (result) {
            VariableSymbol symbol = (VariableSymbol) obj;
            result = this.name.equals(symbol.name);
        }
        return result;
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForVariable(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForVariable(this));
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return secondArgument.callVisitor(new ExponentiationOperationVisitorForVariable(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateVariable(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
