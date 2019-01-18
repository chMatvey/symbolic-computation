package ru.chudakov.symbolic.operation;

import lombok.NoArgsConstructor;
import ru.chudakov.symbolic.AbstractSymbol;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.cache.CacheSymbolSingleton;

@NoArgsConstructor
public abstract class OperationSymbol extends AbstractSymbol {
    protected Symbol checkCache(Symbol argument) {
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        if (cache.getData().containsKey(argument)) {
            return cache.getData().get(argument);
        } else {
            return argument;
        }
    }
}
