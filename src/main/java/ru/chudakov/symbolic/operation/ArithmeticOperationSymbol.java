package ru.chudakov.symbolic.operation;

import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public abstract class ArithmeticOperationSymbol extends OperationSymbol {
    protected TreeSet<Symbol> branches;

    public ArithmeticOperationSymbol(Collection<Symbol> nodes) {
        branches = mergeDuplicates(nodes);
    }

    public ArithmeticOperationSymbol(Symbol firstArgument, Symbol secondArgument) {
        branches = new TreeSet<>();
        branches.add(firstArgument);
        branches.add(secondArgument);
    }

    protected abstract TreeSet<Symbol> mergeDuplicates(@NotNull Collection<Symbol> nodes);

    public abstract void addBranch(Symbol symbol);

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
