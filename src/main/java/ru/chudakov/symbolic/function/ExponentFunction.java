package ru.chudakov.symbolic.function;

public class ExponentFunction implements Function{
    @Override
    public Double getFunctionValue(Double argument) {
        return Math.exp(argument);
    }
}
