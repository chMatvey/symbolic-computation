package ru.chudakov.symbolic.function;

public class ExponentFunction implements Function{
    @Override
    public Number getFunctionValue(Number argument) {
        return Math.exp(argument.doubleValue());
    }
}
