package ru.chudakov.symbolic.operation;

import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;

import java.util.Collection;
import java.util.TreeSet;

public class SumSymbol extends ArithmeticOperationSymbol {

    public SumSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public SumSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }

    @Override
    protected TreeSet<Symbol> mergeDuplicates(@NotNull Collection<Symbol> nodes) {
        TreeSet<Symbol> uniqueCollection = new TreeSet<>();
        Symbol duplicateNode;
        for (Symbol node : nodes) {
            if (uniqueCollection.contains(node)) {
                duplicateNode = uniqueCollection.ceiling(node);
                node = node.add(duplicateNode);
                uniqueCollection.remove(node);
            }
            uniqueCollection.add(node);
        }
        return uniqueCollection;
    }

    @Override
    public void addBranch(Symbol symbol) {
        if (branches.contains(symbol)) {
            Symbol duplicate = branches.ceiling(symbol);
            symbol = symbol.add(duplicate);
            branches.remove(duplicate);
        }
        branches.add(symbol);
    }
}
