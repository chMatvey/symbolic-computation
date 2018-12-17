
package ru.chudakov.symbolic;

import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.function.DefaultFunction;
import ru.chudakov.symbolic.function.Function;

import java.util.Collection;
import java.util.TreeMap;

public class Sum extends Operation {
    private Function function;

    public Sum(Collection<Symbol> nodes, Collection<Symbol> coefficients) {
        super(nodes, coefficients);
        function = new DefaultFunction();
    }

    public Sum(TreeMap<Symbol, Symbol> first, TreeMap<Symbol, Symbol> second) {
        super(first, second);
        function = new DefaultFunction();
    }

    public Sum(Collection<Symbol> nodes, Collection<Symbol> coefficient, Function function) {
        super(nodes, coefficient);
        this.function = function;
    }

    public Sum(TreeMap<Symbol, Symbol> first, TreeMap<Symbol, Symbol> second, Function function) {
        super(first, second);
        this.function = function;
    }

    @Override
    public void add(Symbol symbol) {

    }
}
