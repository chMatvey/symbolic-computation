package symbolic;

import java.util.List;

public class Mul<T extends java.lang.Number> extends Symbol<T> {

    public Mul(int countBranches){
        super(countBranches);
    }

    public Mul(int countBranches, List<Symbol<T>> branches, List<T> coefficients){
        super(countBranches, branches, coefficients);
    }
}
