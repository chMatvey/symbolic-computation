package ru.chudakov.symbolic.cache;

import ru.chudakov.symbolic.Symbol;

import java.util.List;
import java.util.TreeMap;

public class CacheSymbolSingleton {
    private static CacheSymbolSingleton instance;
    private TreeMap<Symbol, Symbol> data;
    private TreeMap<Symbol, List<String>> variableFunction;

    private CacheSymbolSingleton(TreeMap<Symbol, Symbol> data, TreeMap<Symbol, List<String>> variableFunction) {
        this.data = data;
        this.variableFunction = variableFunction;
    }

    public static CacheSymbolSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSymbolSingleton(new TreeMap<>(), new TreeMap<>());
        }
        return instance;
    }

    public void addSymbol(Symbol key, Symbol value) {
        data.put(key, value);
    }

    public void addVariableFunction(Symbol key, List<String> value) {
        variableFunction.put(key, value);
    }

    public TreeMap<Symbol, Symbol> getData() {
        return data;
    }

    public TreeMap<Symbol, List<String>> getVariableFunction() {
        return variableFunction;
    }
}
