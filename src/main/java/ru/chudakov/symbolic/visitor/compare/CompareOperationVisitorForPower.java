package ru.chudakov.symbolic.visitor.compare;

import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.FractionSymbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.*;
import ru.chudakov.symbolic.visitor.OperationVisitor;

public class CompareOperationVisitorForPower {
    private PowerSymbol firstArgument;

    public CompareOperationVisitorForPower(PowerSymbol firstArgument) {
        this.firstArgument = firstArgument;
    }

    private int calculate(NumberSymbol firstArgument, NumberSymbol secondArgument) {
        int result = 0;
//        if (firstArgument.getIndex().getClass() == NumberSymbol.class &&
//                secondArgument.getClass() == NumberSymbol.class) {
//            NumberSymbol first = (NumberSymbol) firstArgument.getIndex();
//            NumberSymbol second = (NumberSymbol) secondArgument;
//            result = first.getData().compareTo(second.getData());
//        } else if (firstArgument.getIndex().getClass() == FractionSymbol.class &&
//                secondArgument.getClass() == FractionSymbol.class) {
//            FractionSymbol first = (FractionSymbol) firstArgument.getIndex();
//            FractionSymbol second = (FractionSymbol) secondArgument;
//            result = first.getData().compareTo(second.getData());
//        } else if (firstArgument.getIndex().getClass() == NumberSymbol.class) {
//            NumberSymbol first = (NumberSymbol) firstArgument.getIndex();
//            FractionSymbol second = (FractionSymbol) secondArgument;
//            result = first.getData().compareTo(second.getData());
//        } else {
//            FractionSymbol first = (FractionSymbol) firstArgument.getIndex();
//            NumberSymbol second = (NumberSymbol) secondArgument;
//            result = first.getData().compareTo(second.getData());
//        }
        result = secondArgument.getData().compareTo(firstArgument.getData());
        return result;
    }

    public int calculatePower(PowerSymbol secondArgument) {
        int result = firstArgument.compareTo(secondArgument);
        if (result == 0) {
            if (firstArgument.getIndex().getPriority() == 0) {
                result = calculate((NumberSymbol) firstArgument.getIndex(),(NumberSymbol) secondArgument.getIndex());
            } else if (firstArgument.getIndex().getPriority() == -2) {
                result = calculate(((ArithmeticOperationSymbol) firstArgument.getIndex()).getCoefficient(),
                        ((ArithmeticOperationSymbol) secondArgument.getIndex()).getCoefficient());
            }
        }
        return result;
    }
}
