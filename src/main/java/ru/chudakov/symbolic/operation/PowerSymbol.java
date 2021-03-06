package ru.chudakov.symbolic.operation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForPower;
import ru.chudakov.symbolic.visitor.exponentiation.ExponentiationOperationVisitorForPower;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForPower;

import javax.xml.bind.annotation.*;
import java.util.TreeMap;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "power")
@XmlAccessorType(XmlAccessType.FIELD)
public class PowerSymbol extends OperationSymbol {
    @XmlAnyElement
    private Symbol base;
    @XmlAnyElement
    private Symbol index;

    public PowerSymbol(Symbol base, Symbol index) {
        this.base = base;
        this.index = index;
    }

    @Override
    public int getPriority() {
        return -3;
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForPower(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForPower(this));
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return secondArgument.callVisitor(new ExponentiationOperationVisitorForPower(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculatePower(this);
    }

    @Override
    public Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values) {
        return base.putValue(values).pow(index.putValue(values));
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            PowerSymbol powerSymbol = (PowerSymbol) o;
            result = base.compareTo(powerSymbol.base);
            if (result == 0){
                result = index.compareTo(powerSymbol.index);
                if (index.getPriority() == 0) {
                    NumberSymbol first = (NumberSymbol) index;
                    NumberSymbol second = (NumberSymbol) powerSymbol.index;
                    result = first.getData().compareTo(second.getData());
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "(" + base.toString() + "^" + index.toString() + ")";
    }
}
