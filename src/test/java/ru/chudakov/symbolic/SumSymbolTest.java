package ru.chudakov.symbolic;

import org.junit.Test;
import ru.chudakov.symbolic.term.NumberTerm;

import java.util.ArrayList;
import java.util.List;

public class SumSymbolTest {

    @Test
    public void createSumSymbol() {
        List<Symbol> dataList = new ArrayList<>();
        List<NumberTerm> coefficientsList = new ArrayList<>();
        dataList.add(new OperandSymbol(new VariableTerm("a")));
        dataList.add(new OperandSymbol(new VariableTerm("b")));
        dataList.add(new OperandSymbol(new VariableTerm("a")));
        dataList.add(new OperandSymbol(new NumberTerm(3d)));
        dataList.add(new OperandSymbol(new NumberTerm(5d)));
        coefficientsList.add(new NumberTerm(1d));
        coefficientsList.add(new NumberTerm(2d));
        coefficientsList.add(new NumberTerm(3d));
        coefficientsList.add(new NumberTerm(4d));
        coefficientsList.add(new NumberTerm(5d));
        Symbol sum = new SumSymbol(dataList, coefficientsList);
        int a = sum.size();
        return;
    }
}
