package ru.chudakov.symbolic.operation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForSum;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.exponentiation.ExponentiationOperationVisitorForSum;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForSum;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "sum")
public class SumSymbol extends ArithmeticOperationSymbol {

    public SumSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public SumSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }

    @XmlAnyElement
    //@XmlElementWrapper(name = "arguments")
    public TreeSet<Symbol> getBranches(){
        return branches;
    }

    @Override
    protected String getOperation() {
        return "+";
    }

    @Override
    protected Symbol calculate(Symbol firstArgument, Symbol secondArgument) {
        return firstArgument.add(secondArgument);
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForSum(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForSum(this));
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return secondArgument.callVisitor(new ExponentiationOperationVisitorForSum(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateSum(this);
    }

    @Override
    protected Symbol getInstance(List<Symbol> branches) {
        return new SumSymbol(branches);
    }
}
