package ru.chudakov.symbolic;

import org.junit.Test;
import ru.chudakov.symbolic.term.Term;
import ru.chudakov.symbolic.term.TermNumber;
import ru.chudakov.symbolic.term.Variable;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class SumTest {
    @Test
    public void createSymbol(){
        Collection<AbstractSymbol<Integer>> collection = new ArrayList<>();
        collection.add(new Sum<>(new Variable<>("a"), new TermNumber<>(2)));
        collection.add(new Sum<>(new Variable<>("b"), new TermNumber<>(3)));
        collection.add(new Sum<>(new Variable<>("b"), new TermNumber<>(2)));
        collection.add(new Sum<>(new TermNumber<>(12)));
        AbstractSymbol<Integer> symbol = new Sum<>(collection);
        System.out.println(symbol);
    }
}
