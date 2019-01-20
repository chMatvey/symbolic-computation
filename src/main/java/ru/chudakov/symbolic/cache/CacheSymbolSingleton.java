package ru.chudakov.symbolic.cache;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.chudakov.symbolic.Symbol;

import java.util.List;
import java.util.TreeMap;

@Getter
public class CacheSymbolSingleton {
    private static CacheSymbolSingleton instance;

    private TreeMap<Symbol, Symbol> variablesAndFunction;
    private TreeMap<String, String> functions;
    private TreeMap<String, List<String>> variableFunction;
    private TreeMap<String, List<Pair<String, String>>> valueVariableFunction;
    private String expression;

    private CacheSymbolSingleton(TreeMap<Symbol, Symbol> variablesAndFunction,
                                 TreeMap<String, String> functions,
                                 TreeMap<String, List<String>> variableFunction,
                                 TreeMap<String, List<Pair<String, String>>> valueVariableFunction) {
        this.variablesAndFunction = variablesAndFunction;
        this.functions = functions;
        this.variableFunction = variableFunction;
        this.valueVariableFunction = valueVariableFunction;
    }

    public static CacheSymbolSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSymbolSingleton(new TreeMap<>(), new TreeMap<>(), new TreeMap<>(), new TreeMap<>());
        }
        return instance;
    }

    public void addFunction(String name, String expression, Symbol function, Symbol value, List<String> variables,
                            List<Pair<String, String>> valueVariables) {
        variablesAndFunction.put(function, value);
        functions.put(name, expression);
        variableFunction.put(name, variables);
        valueVariableFunction.put(name, valueVariables);
    }

    public void addVariable(String name, Symbol variable, Symbol value) {
        functions.remove(name);
        variablesAndFunction.remove(variable);
        variablesAndFunction.put(variable, value);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
