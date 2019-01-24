package ru.chudakov.web.service;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chudakov.reader.FunctionsTreeExpression;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.ListSymbol;
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

    public List<List<List<Double>>> getPoints(String expression) {
        Symbol symbol = functionsTreeExpression.getSymbol(expression);
        if (symbol.getClass() != ListSymbol.class) {
            return null;
        }
        List<List<List<Double>>> result = new ArrayList<>();
        ListSymbol parentListSymbol = (ListSymbol) symbol;
        for (Symbol oneGraphsSymbol : parentListSymbol.getList()) {
            if (oneGraphsSymbol.getClass() != ListSymbol.class) {
                return null;
            }
            ListSymbol oneGraphsListSymbol = (ListSymbol) oneGraphsSymbol;
            List<List<Double>> listAllCoordinateFotOneGraphs = new ArrayList<>();
            for (Symbol pairSymbol : oneGraphsListSymbol.getList()) {
                if (pairSymbol.getClass() != ListSymbol.class) {
                    return null;
                }
                ListSymbol pairListSymbol = (ListSymbol) pairSymbol;
                if (pairListSymbol.getList().size() != 2) {
                    return null;
                }
                Symbol xCoordinateSymbol = pairListSymbol.getList().get(0);
                Symbol yCoordinateSymbol = pairListSymbol.getList().get(1);
                if (xCoordinateSymbol.getClass() != NumberSymbol.class ||
                        yCoordinateSymbol.getClass() != NumberSymbol.class) {
                    return null;
                }
                double xCoordinate = ((NumberSymbol) xCoordinateSymbol).getData();
                double yCoordinate = ((NumberSymbol) yCoordinateSymbol).getData();
                List<Double> pair = new ArrayList<>();
                pair.add(xCoordinate);
                pair.add(yCoordinate);
                listAllCoordinateFotOneGraphs.add(pair);
            }
            result.add(listAllCoordinateFotOneGraphs);
        }
        return result;
    }

    public String calculate(String expression) {
        Symbol result = functionsTreeExpression.getSymbol(expression);
        return result.toString();
    }
}
