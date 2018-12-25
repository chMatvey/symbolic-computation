package ru.chudakov.symbolic.operation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForFraction;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForFunction;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForFunction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class FunctionSymbol extends OperationSymbol {
    @XmlAnyElement
    protected Symbol argument;

    public FunctionSymbol(Symbol argument) {
        this.argument = argument;
    }

    @Override
    public int getPriority() {
        return -4;
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            FunctionSymbol function = (FunctionSymbol) o;
            result = this.toString().compareTo(function.toString());
            if (result == 0) {
                result = this.argument.compareTo(function.argument);
            }
        }
        return result;
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForFunction(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForFunction(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateFunction(this);
    }
}
