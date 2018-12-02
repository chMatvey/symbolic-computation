package symbolic;

import java.util.Collection;
import java.util.TreeSet;

public abstract class AbstractSymbol<T extends Number> implements Symbol<T>, Comparable<AbstractSymbol> {

    protected int countBranches;
    protected TreeSet<Symbol<T>> branches;
    protected Symbol coefficients;

    AbstractSymbol() {
        countBranches = 0;
        branches = null;
        coefficients = createOne();
    }

    AbstractSymbol(AbstractSymbol coefficients) {
        countBranches = 0;
        branches = null;
        this.coefficients = coefficients;
    }

    AbstractSymbol(Collection<AbstractSymbol<T>> branches) {
        this.countBranches = branches.size();
        this.branches = new TreeSet<>(branches);
        this.coefficients = createOne();
    }

    AbstractSymbol(Collection<AbstractSymbol<T>> branches, AbstractSymbol coefficients) {
        this.countBranches = branches.size();
        this.branches = new TreeSet<>(branches);
        this.coefficients = coefficients;
    }

    @Override
    public void addBranch(Symbol<T> branch) {
        countBranches++;
        branches.add(branch);
    }

    public void setCoefficients(Symbol coefficients) {
        this.coefficients = coefficients;
    }

    protected AbstractSymbol createOne() {
        return new Sum<Number>(new Number() {
            @Override
            public int intValue() {
                return 1;
            }

            @Override
            public long longValue() {
                return 1;
            }

            @Override
            public float floatValue() {
                return 1;
            }

            @Override
            public double doubleValue() {
                return 1;
            }
        });

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractSymbol symbol = (AbstractSymbol) obj;
        return countBranches == symbol.countBranches && branches.equals(symbol.branches);
    }

    @Override
    public int compareTo(AbstractSymbol o) {
        if (this.getClass() == o.getClass()) {
            return 0;
        } else if (this.getClass() == Sum.class) {
            return 1;
        } else {
            return -1;
        }
    }
}
