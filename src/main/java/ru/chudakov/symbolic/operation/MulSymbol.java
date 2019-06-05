package ru.chudakov.symbolic.operation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForMul;
import ru.chudakov.symbolic.visitor.exponentiation.ExponentiationOperationVisitorForMul;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForMul;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "mul")
@XmlType
public class MulSymbol extends ArithmeticOperationSymbol {

    public MulSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public MulSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }

    @XmlAnyElement
    public TreeSet<Symbol> getBranches(){
        return branches;
    }

    @Override
    protected String getOperation() {
        return "*";
    }

    @Override
    protected Symbol calculate(Symbol firstArgument, Symbol secondArgument) {
        return firstArgument.mul(secondArgument);
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForMul(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForMul(this));
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return secondArgument.callVisitor(new ExponentiationOperationVisitorForMul(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateMul(this);
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result != 0 && branches.size() == 2 && branches.contains(new NumberSymbol(1d))) {
            result = branches.first().compareTo(o);
        }
        return result;
    }

    @Override
    protected Symbol getInstance(List<Symbol> branches) {
        return new MulSymbol(branches);
    }

    @Override
    protected Symbol getDefaultSymbolToPutValue() {
        return new NumberSymbol(1d);
    }
}
