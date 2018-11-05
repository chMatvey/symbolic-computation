package symbolic;

import java.util.ArrayList;
import java.util.List;

public class Mul<T extends java.lang.Number> extends Symbol<T> {

    public Mul(int countBranches){
        super(countBranches);
    }

    public Mul(int countBranches, List<Symbol<T>> branches, List<T> coefficients){
        super(countBranches, branches, coefficients);
    }

    public void MulTerms(){
        Symbol newThis = new Sum();
        int i = 0;
        List<Symbol<T>> newBranches = new ArrayList<Symbol<T>>();
        for (Symbol<T> s: this.branches) {
            if(s.branches.isEmpty()){
                if(newBranches.isEmpty()){
                    Mul mul = new Mul<T>(0);
                    mul.coefficients.add(this.coefficients.get(i));
                    mul.branches.add(this.branches.get(i));
                    newBranches.add(mul);
                } else {
                    for (int j = 0; j < newBranches.size(); j++){
                        Symbol newMul = newBranches.get(j);
                        newMul.branches.add();
                    }
                }
            } else {
                int countTerms = branches.get(0).branches.size();
                for(int i = 0; i < this.countBranches; i++){

                    newBranches.add(new Mul<T>(0).branches.add()));
                }
            }
        }
    }
}
