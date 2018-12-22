package ru.chudakov.symbolic.operation;

import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public abstract class ArithmeticOperationSymbol extends OperationSymbol {
    private TreeSet<Symbol> branches;

    public ArithmeticOperationSymbol(Collection<Symbol> nodes) {
        branches = mergeDuplicates(nodes);
    }

    public ArithmeticOperationSymbol(Symbol firstArgument, Symbol secondArgument) {
        branches = new TreeSet<>();
        branches.add(firstArgument);
        branches.add(secondArgument);
    }

    private TreeSet<Symbol> mergeDuplicates(@NotNull Collection<Symbol> nodes) {
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

    public void addBranch(Symbol symbol) {
        if (branches.contains(symbol)) {
            Symbol duplicate = branches.ceiling(symbol);
            symbol = symbol.add(duplicate);
            branches.remove(duplicate);
        }
        branches.add(symbol);
    }

    public Symbol[] toArray() {
        return branches.toArray(new Symbol[0]);
    }

    @Override
    public int getPriority() {
        return -2;
    }

    @Override
    public int compareTo(Symbol o) {
        int result = super.compareTo(o);
        if (result == 0) {
            ArithmeticOperationSymbol symbol = (ArithmeticOperationSymbol) o;
            Iterator<Symbol> firstIterator = branches.iterator();
            Iterator<Symbol> secondIterator = symbol.branches.iterator();
            while (firstIterator.hasNext() && secondIterator.hasNext()) {
                result = firstIterator.next().compareTo(secondIterator.next());
                if (result != 0) {
                    return result;
                }
            }
            if (firstIterator.hasNext())
                result = 1;
            else if (secondIterator.hasNext())
                result = -1;
        }
        return result;
    }
}
