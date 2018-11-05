package symbolic;

import java.util.ArrayList;
import java.util.List;

public abstract class Symbol<T extends java.lang.Number> {

    protected int countBranches;
    protected List<Symbol<T>> branches;
    protected List<T> coefficients;

    Symbol(){
        countBranches = 0;
        branches = null;
        coefficients = null;
    }

    Symbol(int countBranches){
        this.countBranches = countBranches;
        branches = new ArrayList<Symbol<T>>(countBranches);
        coefficients = new ArrayList<T>(countBranches);
    }

    Symbol(int countBranches, List<Symbol<T>> branches, List<T> coefficients){
        this.countBranches = countBranches;
        this.branches = branches;
        this.coefficients = coefficients;
    }

    protected void setCoefficients(int index, T coefficient) {
        this.coefficients.add(index, coefficient);
    }
}
