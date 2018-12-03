package ru.chudakov.symbolic.term;

public class TermNumber<T extends Number> extends Magnitude<T> {

    private T data;

    public TermNumber(T data) {
        this.data = data;
    }

    @Override
    public TermTypes getType() {
        return TermTypes.Number;
    }

    @Override
    public String getExpressionName() {
        return data.toString();
    }

    @Override
    public T getValue() {
        return data;
    }

    @Override
    public void setValue(T value) {
        data = value;
    }

    public static TermNumber add(TermNumber a, TermNumber b) {
        if (a == null && b == null) {
            return null;
        } else if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } if (a.getValue() instanceof Double || b.getValue() instanceof Double) {
            return new TermNumber<>(a.getValue().doubleValue() + b.getValue().doubleValue());
        } else if (a.getValue() instanceof Float || b.getValue() instanceof Float) {
            return new TermNumber<>(a.getValue().floatValue() + b.getValue().floatValue());
        } else if (a.getValue() instanceof Long || b.getValue() instanceof Long) {
            return new TermNumber<>(a.getValue().longValue() + b.getValue().floatValue());
        } else {
            return new TermNumber<>(a.getValue().intValue() + b.getValue().intValue());
        }
    }

    public static TermNumber mul(TermNumber a, TermNumber b) {
        if (a.getValue() instanceof Double || b.getValue() instanceof Double) {
            return new TermNumber<>(a.getValue().doubleValue() * b.getValue().doubleValue());
        } else if (a.getValue() instanceof Float || b.getValue() instanceof Float) {
            return new TermNumber<>(a.getValue().floatValue() * b.getValue().floatValue());
        } else if (a.getValue() instanceof Long || b.getValue() instanceof Long) {
            return new TermNumber<>(a.getValue().longValue() * b.getValue().floatValue());
        } else {
            return new TermNumber<>(a.getValue().intValue() * b.getValue().intValue());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TermNumber termNumber = (TermNumber) obj;
        return data.equals(termNumber.data);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
