package ru.chudakov.symbolic.operation;

import javafx.util.Pair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;

import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@Getter
@NoArgsConstructor
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
//                if (uniqueCollection.contains(duplicateNode)) {
//                    duplicateNode.compareTo(uniqueCollection.first());
//                    uniqueCollection.first();
//                }
                uniqueCollection.add(duplicateNode);
                //uniqueCollection.size();
            } else {
                uniqueCollection.add(node);
            }
        }
        return uniqueCollection;
    }

    public Symbol[] toArray() {
        return branches.toArray(new Symbol[0]);
    }

    public NumberSymbol getCoefficient() {
        NumberSymbol coefficient = new NumberSymbol(1d);
        if (branches.contains(coefficient)) {
            return (NumberSymbol) branches.ceiling(coefficient);
        } else {
            return coefficient;
        }
    }

    protected abstract Symbol getInstance(List<Symbol> branches);

    @Override
    public int getPriority() {
        return -2;
    }

    @Override
    public Symbol getFirst() {
        return branches.first();
    }

    @Override
    public Symbol getLast() {
        return branches.last();
    }

    @Override
    public int length() {
//        int result = 0;
//        for (Symbol symbol : branches) {
//            result += symbol.length();
//        }
//        return result;
        return branches.size();
    }

    protected abstract Symbol getDefaultSymbolToPutValue();

    @Override
    public Symbol putValue(TreeMap<VariableSymbol, NumberSymbol> values) {
        List<Symbol> newBranches = new ArrayList<>();
        for (Symbol branch : branches) {
            newBranches.add(branch.putValue(values));
        }
        Symbol result = getDefaultSymbolToPutValue();
        for (Symbol branch : newBranches) {
            result = calculate(result, branch);
        }
        return result;
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
