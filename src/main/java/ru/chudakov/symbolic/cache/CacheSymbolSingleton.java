package ru.chudakov.symbolic.cache;

import ru.chudakov.symbolic.Symbol;

import java.util.TreeMap;

public class CacheSymbolSingleton {
    private static CacheSymbolSingleton instance;
    private TreeMap<Symbol, Symbol> data;

    private CacheSymbolSingleton(TreeMap<Symbol, Symbol> data) {
        this.data = data;
    }

    public static CacheSymbolSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSymbolSingleton(new TreeMap<>());
        }
        return instance;
    }

    public void addSymbol(Symbol key, Symbol value) {
        CacheSymbolSingleton singleton = getInstance();
        singleton.data.put(key, value);
    }

    public TreeMap<Symbol, Symbol> getData() {
        return data;
    }
}
