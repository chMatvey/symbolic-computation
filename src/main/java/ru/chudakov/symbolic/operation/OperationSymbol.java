package ru.chudakov.symbolic.operation;

import javafx.util.Pair;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.descriptor.tld.TldRuleSet;
import ru.chudakov.symbolic.AbstractSymbol;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.cache.CacheSymbolSingleton;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;

import java.util.List;
import java.util.TreeMap;

@NoArgsConstructor
public abstract class OperationSymbol extends AbstractSymbol {
    protected Symbol checkCache(Symbol argument) {
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        if (cache.getVariablesAndFunction().containsKey(argument)) {
            return cache.getVariablesAndFunction().get(argument);
        } else {
            return argument;
        }
    }
}
