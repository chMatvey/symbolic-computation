package ru.chudakov.symbolic.operation;

import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.visitor.Addition.AdditionOperationVisitorForMul;
import ru.chudakov.symbolic.visitor.Addition.AdditionOperationVisitorForSum;
import ru.chudakov.symbolic.visitor.OperationVisitor;

import java.util.Collection;
import java.util.TreeSet;

public class MulSymbol extends ArithmeticOperationSymbol {

    public MulSymbol(Collection<Symbol> nodes) {
        super(nodes);
    }

    public MulSymbol(Symbol firstArgument, Symbol secondArgument) {
        super(firstArgument, secondArgument);
    }

    @Override
    protected TreeSet<Symbol> mergeDuplicates(@NotNull Collection<Symbol> nodes) {
        TreeSet<Symbol> uniqueCollection = new TreeSet<>();
        Symbol duplicateNode;
        for (Symbol node : nodes) {
            if (uniqueCollection.contains(node)) {
                duplicateNode = uniqueCollection.ceiling(node);
                node = node.mul(duplicateNode);
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
            symbol = symbol.mul(duplicate);
            branches.remove(duplicate);
        }
        branches.add(symbol);
    }

    @Override
    public Symbol add(Symbol secondArgument) {
        return secondArgument.callVisitor(new AdditionOperationVisitorForMul(this));
    }

    @Override
    public Symbol mul(Symbol secondArgument) {
        return super.mul(secondArgument);
    }

    @Override
    public Symbol callVisitor(OperationVisitor visitor) {
        return super.callVisitor(visitor);
    }
}
