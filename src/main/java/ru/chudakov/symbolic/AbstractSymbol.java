package ru.chudakov.symbolic;

import ru.chudakov.symbolic.term.Term;
import ru.chudakov.symbolic.term.TermNumber;
import ru.chudakov.symbolic.term.TermTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public abstract class AbstractSymbol<T extends Number> implements Symbol<T> {

    protected int countBranches;
    protected TreeSet<AbstractSymbol<T>> branches;
    protected TermNumber<T> coefficient;

    AbstractSymbol() {
        countBranches = 0;
        branches = null;
        coefficient = null;
    }

    AbstractSymbol(TermNumber<T> coefficients) {
        countBranches = 0;
        branches = null;
        this.coefficient = coefficients;
    }

    AbstractSymbol(Collection<AbstractSymbol<T>> branches) {
        this.countBranches = branches.size();
        this.branches = new TreeSet<>(mergeDuplicate(branches));
        this.coefficient = null;
    }

    AbstractSymbol(Collection<AbstractSymbol<T>> branches, TermNumber<T> coefficients) {
        this.countBranches = branches.size();
        this.branches = new TreeSet<>(mergeDuplicate(branches));
        this.coefficient = coefficients;
    }

    public Collection<AbstractSymbol<T>> mergeDuplicate(Collection<AbstractSymbol<T>> duplicateCollection) {
        TreeSet<AbstractSymbol<T>> uniqueCollection = new TreeSet<>();
        List<AbstractSymbol> duplicates = new ArrayList<>();
        for (AbstractSymbol<T> symbol : duplicateCollection) {
            if (uniqueCollection.contains(symbol)) {
                duplicates.add(uniqueCollection.ceiling(symbol));
                if (symbol.getClass() == Sum.class) {
                    Sum sum = (Sum) symbol;
                    if (sum.getData().getType() == TermTypes.Variable) {
                        symbol.coefficient = TermNumber.add(symbol.coefficient,
                                duplicates.get(duplicates.size() - 1).coefficient
                        );
                    } else if (sum.getData().getType() == TermTypes.Number) {
                        Sum duplicate = (Sum) duplicates.get(duplicates.size() - 1);
                        if (this.getClass() == Sum.class) {
                            sum.setData((Term) TermNumber.add((TermNumber) sum.getData(),
                                    (TermNumber) duplicate.getData()
                            ));
                        } else {
                            sum.setData((Term) TermNumber.mul((TermNumber) sum.getData(),
                                    (TermNumber) duplicate.getData()
                            ));
                        }
                    } else {
                        ///////////
                    }
                }
                uniqueCollection.remove(symbol);
            }
            uniqueCollection.add(symbol);
        }
        return uniqueCollection;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractSymbol symbol = (AbstractSymbol) obj;
        return countBranches == symbol.countBranches && branches.equals(symbol.branches);
    }

    @Override
    public int compareTo(Symbol o) {
        if (this.getClass() == o.getClass()) {
            return 0;
        } else if (this.getClass() == Sum.class) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (AbstractSymbol symbol : branches) {
            if (symbol.coefficient != null){
                result += symbol.coefficient;
            }
            result += symbol.toString() + "+";
        }
        return result.substring(0, result.length()-1);
    }

    @Override
    public void add(Symbol symbol) {

    }

    @Override
    public void mul(Symbol symbol) {

    }

    @Override
    public void power(Symbol symbol) {

    }
}
