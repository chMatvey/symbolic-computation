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

    @PostConstruct
    public void init() {
        functionsTreeExpression = new FunctionsTreeExpression();
    }

    public List<List<Double>> getPoints(String expression, Double leftBorder,
                                                Double rightBorder, int countSplits) {
        Symbol equation = functionsTreeExpression.getSymbol(expression);
        TreeSet<VariableSymbol> variables = functionsTreeExpression.getVariables(expression);
        if (equation == null || leftBorder >= rightBorder || countSplits < 1 || variables.size() != 1) {
            return null;
        }
        double delta = (rightBorder - leftBorder) / countSplits;
        VariableSymbol variable = variables.first();
        TreeMap<VariableSymbol, NumberSymbol> values = new TreeMap<>();
        values.put(variable, new NumberSymbol(leftBorder));
        List<List<Double>> result = new ArrayList<>();
        for (int i = 0; i < countSplits; i++) {
            List<Double> pair = new ArrayList<>();
            pair.add(leftBorder);
            Symbol value = equation.putValue(values);
            if (value.getClass() != NumberSymbol.class) {
                return null;
            }
            NumberSymbol numberValue = (NumberSymbol) value;
            pair.add(
                    numberValue.getData()
            );
            result.add(pair);
            leftBorder += delta;
            values.clear();
            values.put(variable, new NumberSymbol(leftBorder));
        }
        List<Double> lastPair = new ArrayList<>();
        lastPair.add(rightBorder);
        lastPair.add(result.remove(countSplits - 1).get(1));
        result.add(lastPair);
        return result;
    }
}
