package symbolic;

import symbolic.term.*;
import symbolic.term.Number;

import java.util.List;

public class Sum<T extends java.lang.Number> extends Symbol<T> {
    private Terms<T> data;

    public Sum(){
        super();
    }

    public Sum(T number){
        super();
        data = new Number<>(number);
    }

    private Sum(TermTypes termTypes){
        super();
        switch (termTypes){
            case Exponent:{
                data = new Exponent<>();
                break;
            }
            default:{
                break;
            }
        }
    }

    public Sum(int countBranches){
        super(countBranches);
    }

    public Sum(String variableName){
        super();
        data = new Variable<>(variableName);
    }

    public Sum(int countBranches, List<Symbol<T>> branches, List<T> coefficients){
        super(countBranches, branches, coefficients);
    }

    private Sum(TermTypes termTypes, int countBranches, List<Symbol<T>> branches, List<T> coefficients){
        super(countBranches, branches, coefficients);
        switch (termTypes){
            case Exponent:{
                data = new Exponent<>();
                break;
            }
            default:{
                break;
            }
        }
    }

    
}
