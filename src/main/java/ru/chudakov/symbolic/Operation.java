package ru.chudakov.symbolic;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class Operation implements Symbol {
    protected TreeMap<Symbol, Symbol> branches;

    public Operation(Collection<Symbol> nodes, Collection<Symbol> coefficients) {
        branches = mergeDuplicates(nodes, coefficients);
    }

    protected Operation(TreeMap<Symbol, Symbol> first, TreeMap<Symbol, Symbol> second) {
        List<Symbol> nodes = new ArrayList<>();
        List<Symbol> coefficients = new ArrayList<>();
        for (Map.Entry<Symbol, Symbol> entry: first.entrySet()){
            nodes.add(entry.getKey());
            coefficients.add(entry.getValue());
        }
        for (Map.Entry<Symbol, Symbol> entry: second.entrySet()){
            nodes.add(entry.getKey());
            coefficients.add(entry.getValue());
        }
        branches = mergeDuplicates(nodes, coefficients);
    }

    private TreeMap<Symbol, Symbol> mergeDuplicates(Collection<Symbol> nodes,
                                                    Collection<Symbol> coefficient) {
        TreeMap<Symbol, Symbol> uniqueCollection = new TreeMap<>();
        Symbol[] nodesArray = nodes.toArray(new Symbol[0]);
        Symbol[] coefficientArray = coefficient.toArray(new Symbol[0]);
        for (int i = 0; i < nodesArray.length; i++) {
            if (uniqueCollection.containsKey(nodesArray[i])) {
                Symbol duplicate = uniqueCollection.remove(nodesArray[i]);
                coefficientArray[i].add(duplicate);
            }
            uniqueCollection.put(nodesArray[i], coefficientArray[i]);
        }
        return uniqueCollection;
    }

    public TreeMap<Symbol, Symbol> getBranches() {
        return branches;
    }

    @Override
    public int size() {
        return branches.size();
    }

    @Override
    public Symbol getFirst() {
        return branches.firstKey().getFirst();
    }

    @Override
    public int compareTo(@NotNull Symbol o) {
        if (this.branches.size() < o.size())
            return -1;
        else if (this.branches.size() == o.size())
            return this.getFirst().compareTo(o.getFirst());
        else
            return 1;
    }
}
