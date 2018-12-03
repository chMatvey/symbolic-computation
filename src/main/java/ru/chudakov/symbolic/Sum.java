package ru.chudakov.symbolic;

import ru.chudakov.symbolic.term.AbstractTerm;
import ru.chudakov.symbolic.term.Term;
import ru.chudakov.symbolic.term.TermNumber;
import ru.chudakov.symbolic.term.TermTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class Sum<T extends Number> extends AbstractSymbol<T> {

    private Term<T> data;

    public Sum() {
    }

    public Sum(T number) {
        data = new TermNumber<T>(number);
    }

    public Sum(Term<T> data) {
        this.data = data;
    }

    Sum(Collection<AbstractSymbol<T>> branches) {
        super(branches);
    }

    public Sum(Term<T> data, TermNumber<T> coefficient) {
        super(coefficient);
        this.data = data;
    }

    public Term<T> getData() {
        return data;
    }

    public void setData(Term<T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() || super.equals(obj)) return false;
        Sum sum = (Sum) obj;
        if (countBranches != sum.countBranches) {
            return false;
        }
        if (countBranches == 0) {
            return data.equals(sum.data);
        } else {
            return branches.equals(sum.branches);
        }
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            Sum sum = (Sum) o;
            if (countBranches == 0) {
                result = data.compareTo(sum.data);
            } else {
                for (AbstractSymbol s : this.branches) {
                    AbstractSymbol first = (AbstractSymbol) sum.branches.first();
                    result = s.compareTo(first);
                    sum.branches.remove(first);
                    if (result != 0) break;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (data == null) {
            return super.toString();
        } else {
            return data.toString();
        }
    }
}
