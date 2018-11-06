package symbolic;

import symbolic.term.Number;

import java.util.ArrayList;
import java.util.List;

public abstract class Symbol<T extends java.lang.Number> {

    protected int countBranches;
    protected List<Symbol<T>> branches;
    protected List<Number> coefficients;

    Symbol(){
        countBranches = 0;
        branches = null;
        coefficients = null;
    }

    Symbol(int countBranches){
        this.countBranches = countBranches;
        branches = new ArrayList<Symbol<T>>(countBranches);
        coefficients = new ArrayList<Number>(countBranches);
    }

    Symbol(int countBranches, List<Symbol<T>> branches, List<Number> coefficients){
        this.countBranches = countBranches;
        this.branches = branches;
        this.coefficients = coefficients;
    }

    public void checkEquals(){
        if(branches.size() < 2){
            return;
        }
        for (int i = 0; i < branches.size(); i++){
            for (int j = 1; j < branches.size(); i++){
                if(branches.get(i).equals(branches.get(j))){
                    Number newCoefficient = Number.add(coefficients.get(i), coefficients.get(j));
                    coefficients.set(i, newCoefficient);
                    branches.remove(j);
                    coefficients.remove(j);
                }
            }
        }
    }

    public void addBranch(Symbol<T> branch, Number coefficient){
        countBranches++;
        branches.add(branch);
        coefficients.add(coefficient);
    }
}
