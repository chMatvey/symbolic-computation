package ru.chudakov.symbolic.function;

public class DefaultFunction implements Function {
    @Override
    public Number getFunctionValue(Number argument) {
        return argument;
    }
}
