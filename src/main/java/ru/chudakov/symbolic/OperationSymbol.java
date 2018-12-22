package ru.chudakov.symbolic;

import ru.chudakov.symbolic.term.NumberTerm;

import java.util.*;

public abstract class OperationSymbol extends AbstractSymbol {
    protected TreeMap<Symbol, NumberTerm> branches;

    public OperationSymbol(Collection<Symbol> nodes, Collection<NumberTerm> coefficients) {
        branches = mergeDuplicates(nodes, coefficients);
    }

    private TreeMap<Symbol, NumberTerm> mergeDuplicates(Collection<Symbol> nodes,
                                                        Collection<NumberTerm> coefficient) {
        TreeMap<Symbol, NumberTerm> uniqueCollection = new TreeMap<>();
        Symbol[] nodesArray = nodes.toArray(new Symbol[0]);
        NumberTerm[] coefficientArray = coefficient.toArray(new NumberTerm[0]);
        NumberTerm duplicateCoefficient;
        for (int i = 0; i < nodesArray.length; i++) {
            if (uniqueCollection.containsKey(nodesArray[i])) {
                duplicateCoefficient = uniqueCollection.remove(nodesArray[i]);
                coefficientArray[i].add(duplicateCoefficient);
            }
            uniqueCollection.put(nodesArray[i], coefficientArray[i]);
        }
        return uniqueCollection;
    }

    @Override
    public int size() {
        int result = 0;
        for (Map.Entry<Symbol, NumberTerm> branch : branches.entrySet()) {
            result += branch.getKey().size();
        }
        return result;
    }

    @Override
    public int compareTo(Symbol o) {
        if (this.branches.size() < o.size())
            return -1;
        else if (this.branches.size() == o.size()) {
            int result = 0;
            OperationSymbol operation = (OperationSymbol) o;
            Iterator<Map.Entry<Symbol, NumberTerm>> firstIterator =  branches.entrySet().iterator();
            Iterator<Map.Entry<Symbol, NumberTerm>> secondIterator =  branches.entrySet().iterator();
            while (firstIterator.hasNext() && secondIterator.hasNext()){
                result = firstIterator.next().getKey().compareTo(
                        secondIterator.next().getKey()
                );
                if (result != 0) return result;
            }
            return 0;
        } else
            return 1;
    }
}
