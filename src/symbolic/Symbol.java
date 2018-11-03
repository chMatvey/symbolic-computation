package symbolic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Symbol<T> {
    Symbol(){}

    Symbol(int countBranches, Class<T> tClass){
        this.countBranches = countBranches;
        branches = new ArrayList<Symbol<T>>(countBranches);
        @SuppressWarnings("uncheked")
        final T[] a = (T[]) Array.newInstance(tClass, countBranches);

        isNextChildVariable = new boolean[countBranches];
    }

    public int countBranches;
    public List<Symbol<T>> branches;
    public T[] coefficients;
    public boolean[] isNextChildVariable;
}
