package ru.chudakov;

public class Main {
    public static void main(String[] args) {
    }
}

class Result {

}

interface Symbol {
    public Symbol add(Symbol symbol);

    public Symbol getResult(Symbol symbol);

    public MyVisitor getVisitor();

    public void setVisitor(MyVisitor visitor);
}

abstract class AbstractSymbol implements Symbol {
    protected Symbol left;
    protected Symbol right;
    protected MyVisitor visitor;

    @Override
    public MyVisitor getVisitor() {
        return visitor;
    }

    @Override
    public void setVisitor(MyVisitor visitor) {
        this.visitor = visitor;
    }
}

abstract class Operation extends AbstractSymbol {

}

abstract class Operand extends AbstractSymbol {

}

class ExpressionOperand {

}

class NumberOperand extends Operand {
    double data;

    NumberOperand() {
        left = null;
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol getResult(Symbol symbol) {
        return null;
    }
}

class FractionOperand extends Operand {
    double data;
    double denominator;

    FractionOperand() {
        left = null;
    }

    @Override
    public Symbol add(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol getResult(Symbol symbol) {
        return null;
    }
}

class SumOperation extends Operation {
    SumOperation(Symbol left, Symbol right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Symbol getResult(Symbol symbol) {
        return left.add(right);
    }

    @Override
    public Symbol add(Symbol right) {
        right.setVisitor(new AddVisitorSum());
        return right.getVisitor().calculateSum(this);
    }


}

interface MyVisitor {
    public Symbol calculateNumber(Symbol symbol);
    public Symbol calculateVariable(Symbol symbol);
    public Symbol calculateFraction(Symbol symbol);
    public Symbol calculateSum(Symbol symbol);

}

class AddVisitorNumber implements MyVisitor {
    private Number number;

    @Override
    public Symbol calculateNumber(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateVariable(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateFraction(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateSum(Symbol symbol) {
        return null;
    }
}

class AddVisitorSum implements MyVisitor {
    private SumOperation sumOperation;

    @Override
    public Symbol calculateNumber(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateVariable(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateFraction(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateSum(Symbol symbol) {
        return null;
    }
}

class MulVisitorSum implements MyVisitor {
    private SumOperation sumOperation;

    @Override
    public Symbol calculateNumber(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateVariable(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateFraction(Symbol symbol) {
        return null;
    }

    @Override
    public Symbol calculateSum(Symbol symbol) {
        return null;
    }
}
