package fte;

import symbolic.Sum;
import symbolic.Symbol;
import symbolic.term.TermTypes;

import java.util.List;

public class FunctionTE {

    private String expression;

    FunctionTE(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public Symbol<java.lang.Number> getSymbolic() {
        Symbol result;
        NumberType numberType;
        if (expression.contains(".")){
            result = new Sum<Double>();
            numberType = NumberType.Double;
        } else {
            result = new Sum<Integer>();
            numberType = NumberType.Integer;
        }

        char[] chars = expression.toCharArray();
        if (!(chars[0] == '(' ||
                (chars[0] > 47 && chars[0] < 58) ||
                (chars[0] > 64 && chars[0] < 91) ||
                (chars[0] > 96 && chars[0] < 123))) {
            return null;
        }

        String str = "";
        TermTypes termTypes = null;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 47 && chars[i] < 58) {
                if(!checkTermsType(termTypes, TermTypes.Number)){
                    return null;
                }
                termTypes = TermTypes.Number;
                str += chars[i];
            } else if ((chars[i] > 64 && chars[i] < 91) || (chars[i] > 96 && chars[i] < 123)) {
                if(!checkTermsType(termTypes, TermTypes.Number)){
                    return null;
                }
                termTypes = TermTypes.Variable;
                str += chars[i];
            } else if (chars[i] == '.'){
                if (termTypes != TermTypes.Number){
                    return null;
                }
                str += chars[i];
            } else if (chars[i] == '+'){

            }
        }
        return null;
    }

    private boolean checkTermsType(TermTypes a, TermTypes b){
        return a == b || a == null || b == null;
    }
}
