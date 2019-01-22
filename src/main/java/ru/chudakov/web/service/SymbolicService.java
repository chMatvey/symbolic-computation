package ru.chudakov.web.service;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chudakov.reader.FunctionsTreeExpression;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

@NoArgsConstructor
@Service
public class SymbolicService {
    private FunctionsTreeExpression functionsTreeExpression;
    private final static int withBorder = 1;

    @PostConstruct
    public void init() {
        functionsTreeExpression = new FunctionsTreeExpression();
    }

    public List<List<Double>> getPoints(String expression, Double leftBorder,
                                        Double rightBorder, Double delta) {
        Symbol equation = functionsTreeExpression.getSymbol(expression);
        TreeSet<VariableSymbol> variables = functionsTreeExpression.getVariables(expression);
        if (equation == null || leftBorder >= rightBorder
                || variables.size() != 1) {
            return null;
        }
        VariableSymbol variable = variables.first();
        TreeMap<VariableSymbol, NumberSymbol> values = new TreeMap<>();
        values.put(variable, new NumberSymbol(leftBorder));
        List<List<Double>> result = new ArrayList<>();
        List<Double> pair;
        Symbol valueUserFunction;
        while (leftBorder <= rightBorder) {
            pair = new ArrayList<>();
            pair.add(leftBorder);
            valueUserFunction = equation.putValue(values);
            if (valueUserFunction.getClass() != NumberSymbol.class) {
                return null;
            }
            NumberSymbol numberValue = (NumberSymbol) valueUserFunction;
            pair.add(numberValue.getData());
            result.add(pair);
            leftBorder += delta;
            values.clear();
            values.put(variable, new NumberSymbol(leftBorder));
        }
//        values.clear();
//        values.put(variable, new NumberSymbol(rightBorder));
//        pair = new ArrayList<>();
//        pair.add(rightBorder);
//        valueUserFunction = equation.putValue(values);
//        if (valueUserFunction.getClass() != NumberSymbol.class) {
//            return null;
//        }
//        pair.add(((NumberSymbol) valueUserFunction).getData());
//        result.add(pair);
        List<Double> lastPair = new ArrayList<>();
        lastPair.add(rightBorder);
        lastPair.add(result.remove(result.size() - 1).get(1));
        result.add(lastPair);
        return result;
    }

    private double range(double number) {
        return (double) Math.round(number*1000) / 1000;
    }
}
