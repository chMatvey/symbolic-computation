package ru.chudakov.symbolic.operation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;

import java.util.*;

@NoArgsConstructor
@Getter
public abstract class ArithmeticOperationSymbol extends OperationSymbol {
    protected TreeSet<Symbol> branches;

    public ArithmeticOperationSymbol(Collection<Symbol> nodes) {
        branches = mergeDuplicates(nodes);
    }

    public ArithmeticOperationSymbol(@NotNull Symbol firstArgument, Symbol secondArgument) {
        firstArgument = checkCache(firstArgument);
        secondArgument = checkCache(secondArgument);
        branches = new TreeSet<>();
        if (firstArgument.compareTo(secondArgument) == 0) {
            branches.add(calculate(firstArgument, secondArgument));
        } else {
            branches.add(firstArgument);
            branches.add(secondArgument);
        }
    }

    protected abstract Symbol calculate(Symbol firstArgument, Symbol secondArgument);

    protected abstract String getOperation();

    protected TreeSet<Symbol> mergeDuplicates(@NotNull Collection<Symbol> nodes) {
        TreeSet<Symbol> uniqueCollection = new TreeSet<>();
        Symbol duplicateNode;
        for (Symbol node : nodes) {
            node = checkCache(node);
            if (uniqueCollection.contains(node)) {
                duplicateNode = uniqueCollection.ceiling(node);
                duplicateNode = calculate(node, duplicateNode);
                uniqueCollection.remove(node);
                if (uniqueCollection.contains(duplicateNode)) {
                    duplicateNode.compareTo(uniqueCollection.first());
                    uniqueCollection.first();
                }
                uniqueCollection.add(duplicateNode);
                uniqueCollection.size();
            } else {
                uniqueCollection.add(node);
            }
        }
        return uniqueCollection;
    }

//    public void addBranch(Symbol symbol) {
//        symbol = checkCache(symbol);
//        if (branches.contains(symbol)) {
//            Symbol duplicate = branches.ceiling(symbol);
//            symbol = calculate(symbol, duplicate);
//            branches.remove(duplicate);
//        }
//        branches.add(symbol);
//    }

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
            while (firstIterator.hasNext()) {
                if (firstIterator.next().getPriority() != 0)
                    return -1;
            }
            while (secondIterator.hasNext()) {
                if (secondIterator.next().getPriority() != 0) {
                    return 1;
                }
            }
        }
        return result;
    }

//    @Override
//    public boolean equals(Object obj) {
//        boolean result = super.equals(obj);
//        if (result) {
//            ArithmeticOperationSymbol symbol = (ArithmeticOperationSymbol) obj;
//            Iterator<Symbol> firstIterator = this.branches.iterator();
//            Iterator<Symbol> secondIterator = symbol.branches.iterator();
//            while (firstIterator.hasNext() && secondIterator.hasNext()) {
//                result = firstIterator.next().equals(secondIterator.next());
//                if (!result) {
//                    return false;
//                }
//            }
//            while (firstIterator.hasNext()) {
//                if (firstIterator.next().getPriority() != 0) {
//                    return false;
//                }
//            }
//            while (secondIterator.hasNext()) {
//                if (secondIterator.next().getPriority() != 0) {
//                    return false;
//                }
//            }
//        }
//        return result;
//    }


    @Override
    public String toString() {
        String result = "(";
        for (Symbol symbol : branches) {
            result += symbol.toString() + getOperation();
        }
        return result.substring(0, result.length() - 1) + ")";
    }
}
