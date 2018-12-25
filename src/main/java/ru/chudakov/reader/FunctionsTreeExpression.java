package ru.chudakov.reader;

import org.apache.commons.lang.math.NumberUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;
import ru.chudakov.symbolic.operation.SumSymbol;

import java.util.*;

public class FunctionsTreeExpression {
    private List<String> functions;
    private List<String> variables;
    private List<String> operators;
    private final String separator = ",";
    private final String openBracket = "(";
    private final String closeBracket = ")";

    private Stack<String> stackOperation = new Stack<>();
    private Stack<String> stackReservePolishNotation = new Stack<>();
    //private Stack<String> stackResult = new Stack<>();

    public FunctionsTreeExpression() {
        functions = new ArrayList<>();
        functions.add("sin");
        functions.add("cos");
        functions.add("tg");
        functions.add("ctg");
        variables = new ArrayList<>();
        variables.add("a");
        variables.add("b");
        variables.add("c");
        variables.add("d");
        variables.add("e");
        variables.add("f");
        operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }

    @Contract(pure = true)
    private boolean isFunction(String token) {
        return functions.contains(token);
    }

    @Contract(pure = true)
    private boolean isSeparator(@NotNull String token) {
        return token.equals(separator);
    }

    @Contract(pure = true)
    private boolean isOpenBracket(@NotNull String token) {
        return token.equals(openBracket);
    }

    @Contract(pure = true)
    private boolean isCloseBracket(@NotNull String token) {
        return token.equals(closeBracket);
    }

    @Contract(pure = true)
    private boolean isOperator(String token) {
        return operators.contains(token);
    }

    private boolean isNumberOrVariable(String token) {
        return NumberUtils.isNumber(token) || variables.contains(token);
    }

    private byte getPrecedence(@NotNull String token) {
        if (token.equals("+") || token.equals("-"))
            return 1;
        return 2;
    }

    private void parse(String expression) {
        stackOperation.clear();
        stackReservePolishNotation.clear();
        expression = expression
                .replace(" ", "")
                .replace(",-", ",0-")
                .replace("(-", "(0-");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(
                expression, operators + separator + openBracket + closeBracket, true
        );
        String token;
        while (stringTokenizer.hasMoreTokens()) {
            token = stringTokenizer.nextToken();
            if (isNumberOrVariable(token)) {
                stackReservePolishNotation.push(token);
            } else if (isFunction(token)) {
                stackOperation.push(token);
            } else if (isSeparator(token)) {
                while (!stackOperation.empty() && !isOpenBracket(stackOperation.lastElement())) {
                    stackReservePolishNotation.push(stackOperation.pop());
                }
            } else if (isOperator(token)) {
                while (!stackOperation.empty()
                        && isOperator(stackOperation.lastElement())
                        && getPrecedence(token) <= getPrecedence(stackOperation.lastElement())) {
                    stackReservePolishNotation.push(stackOperation.pop());
                }
                stackOperation.push(token);
            } else if (isOpenBracket(token)) {
                stackOperation.push(token);
            } else if (isCloseBracket(token)) {
                while (!stackOperation.empty() && !isOpenBracket(stackOperation.lastElement())) {
                    stackReservePolishNotation.push(stackOperation.pop());
                }
                stackOperation.pop();
                if (!stackOperation.empty() && isFunction(stackOperation.lastElement())) {
                    stackReservePolishNotation.push(stackOperation.pop());
                }
            }
        }
        while (!stackOperation.empty()) {
            stackReservePolishNotation.push(stackOperation.pop());
        }
        Collections.reverse(stackReservePolishNotation);
//        for (String str : stackReservePolishNotation) {
//            System.out.println(str);
//        }
    }

    public Symbol getSymbol(String expression) {
        parse(expression);
        Symbol result;
        if (NumberUtils.isNumber(stackReservePolishNotation.lastElement())) {
            result = new NumberSymbol(Double.parseDouble(stackReservePolishNotation.pop()));
        } else {
            result = new VariableSymbol(stackReservePolishNotation.pop());
        }
        Symbol secondOperand = null;
        while (!stackReservePolishNotation.empty()) {
            String lastElement = stackReservePolishNotation.pop();
            if (!isOperator(lastElement)) {
                if (NumberUtils.isNumber(lastElement)) {
                    secondOperand = new NumberSymbol(Double.parseDouble(lastElement));
                } else {
                    secondOperand = new VariableSymbol(lastElement);
                }
            } else if (lastElement.equals("+")) {
                result = result.add(secondOperand);
            } else if (lastElement.equals("-")) {
                result = result.add(new MulSymbol(secondOperand, new NumberSymbol(-1d)));
            } else if (lastElement.equals("*")) {
                result = result.mul(secondOperand);
            }
        }
        return result;
    }
}
