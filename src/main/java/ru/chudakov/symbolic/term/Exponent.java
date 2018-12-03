package ru.chudakov.symbolic.term;

import java.util.List;

public class Exponent<T extends Number> extends Function<T> {

    private static final TermTypes types = TermTypes.Exponent;

    @Override
    public TermTypes getType() {
        return TermTypes.Exponent;
    }

    @Override
    public String getExpressionName() {
        return types.toString();
    }

    @Override
    public Number getFunctionValue(T argument) {
        return Math.exp(argument.doubleValue());
    }
}
