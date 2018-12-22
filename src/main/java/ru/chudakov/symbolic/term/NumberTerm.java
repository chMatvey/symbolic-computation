package ru.chudakov.symbolic.term;

import ru.chudakov.symbolic.visitor.term.NumberTermVisitor;
import ru.chudakov.symbolic.visitor.term.TermVisitor;

public class NumberTerm extends AbstractTerm {
    protected TermVisitor visitor;

    public NumberTerm(Double data) {
        super(data);
    }

    public NumberTerm add(NumberTerm secondTerm) {
        return secondTerm.callVisitor(new NumberTermVisitor(this));
    }

    protected NumberTerm callVisitor(TermVisitor visitor) {
        this.visitor = visitor;
        return visitor.addNumber(this);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
