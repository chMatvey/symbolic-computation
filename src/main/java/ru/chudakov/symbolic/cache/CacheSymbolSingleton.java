package ru.chudakov.symbolic.cache;

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
    private String expression;

    private CacheSymbolSingleton(TreeMap<Symbol, Symbol> variablesAndFunction,
                                 TreeMap<String, String> functions,
                                 TreeMap<String, List<String>> variableFunction) {
        this.variablesAndFunction = variablesAndFunction;
        this.functions = functions;
        this.variableFunction = variableFunction;
    }

    public static CacheSymbolSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSymbolSingleton(new TreeMap<>(), new TreeMap<>(), new TreeMap<>());
        }
        return instance;
    }

    public void addFunction(String name, String expression, Symbol function, Symbol value, List<String> variables) {
        variablesAndFunction.put(function, value);
        functions.put(name, expression);
        variableFunction.put(name, variables);
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
