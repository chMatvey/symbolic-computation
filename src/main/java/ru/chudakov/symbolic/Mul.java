package ru.chudakov.symbolic;

import ru.chudakov.symbolic.term.Term;
import ru.chudakov.symbolic.term.TermNumber;
import ru.chudakov.symbolic.term.TermTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class Mul<T extends Number> extends AbstractSymbol<T> {

    public Mul(){
    }

    public Mul(Collection<AbstractSymbol<T>> branches, TermNumber<T> coefficients){
        super(branches, coefficients);
    }

    @Override
    public String toString() {
        String result = "";
        for (AbstractSymbol symbol : branches) {
            result += symbol.toString() + "^" + symbol.coefficient.toString();
        }
        return result;
    }
}
