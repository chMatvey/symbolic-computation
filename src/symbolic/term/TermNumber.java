package symbolic.term;

public class TermNumber<T extends Number> extends Magnitude<T> {

    private T data;

    public TermNumber(T data){
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

    public static TermNumber add(TermNumber a, TermNumber b){
        TermNumber result = null;
        if(a.getValue() instanceof Double || b.getValue() instanceof Double){
            result = new TermNumber<Double>(a.getValue().doubleValue() + b.getValue().doubleValue());
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof Integer){
            result = new TermNumber<Integer>(a.getValue().intValue() + b.getValue().intValue());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TermNumber termNumber = (TermNumber) obj;
        return data.equals(termNumber.data);
    }
}
