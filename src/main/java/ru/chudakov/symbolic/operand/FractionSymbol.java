package ru.chudakov.symbolic.operand;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.addition.AdditionOperationVisitorForFraction;
import ru.chudakov.symbolic.visitor.OperationVisitor;
import ru.chudakov.symbolic.visitor.exponentiation.ExponentiationOperationVisitorForFraction;
import ru.chudakov.symbolic.visitor.multiplication.MultiplicationOperationVisitorForFraction;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "fraction")
public class FractionSymbol extends NumberSymbol {
    private Double denominator;

    public Double getNumerator(){
        return data;
    }

    @XmlElement(name = "numerator")
    public void setNumerator(Double numerator) {
        data = numerator;
    }

    public FractionSymbol(Double numerator, Double denominator) {
        super(numerator);
        this.denominator = denominator;
    }

    @Override
    public Double getData() {
        return data / denominator;
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForFraction(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return secondArgument.callVisitor(new MultiplicationOperationVisitorForFraction(this));
    }

    @Override
    public Symbol pow(Symbol secondArgument) {
        return secondArgument.callVisitor(new ExponentiationOperationVisitorForFraction(this));
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return visitor.calculateFraction(this);
    }

    @Override
    public String toString() {
        return data.toString() + "/" + denominator.toString();
    }
}
