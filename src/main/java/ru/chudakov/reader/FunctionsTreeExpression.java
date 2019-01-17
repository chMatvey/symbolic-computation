package ru.chudakov.reader;

import org.apache.commons.lang.math.NumberUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.cache.CacheSymbolSingleton;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.FunctionSymbol;
import ru.chudakov.symbolic.operation.MulSymbol;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionsTreeExpression {
    private List<String> functions;
    private List<String> variables;
    private List<String> operators;
    private final String separator = ",";
    private final String openBracket = "(";
    private final String closeBracket = ")";

    private Stack<String> stackOperation = new Stack<>();
    private Stack<String> stackReservePolishNotation = new Stack<>();
    private Stack<String> stackValuesOfVariables = new Stack<>();
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
        variables.add("g");
        variables.add("h");
        variables.add("i");
        variables.add("j");
        variables.add("k");
        variables.add("l");
        variables.add("m");
        variables.add("n");
        variables.add("o");
        variables.add("p");
        variables.add("q");
        variables.add("r");
        variables.add("s");
        variables.add("t");
        variables.add("w");
        variables.add("x");
        variables.add("y");
        variables.add("z");
        operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
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

    private boolean isFunction(String token) {
        Pattern pattern = Pattern.compile("\\w+\\(\\w+(,\\w+)*\\)");
        return pattern.matcher(token).matches();
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
    }

    private Symbol readSymbolFromStack() {
        Symbol result;
        String lastElement = stackReservePolishNotation.pop();;
        if (!stackValuesOfVariables.empty() && !NumberUtils.isNumber(lastElement)) {
            lastElement = stackValuesOfVariables.pop();
        }
        if (NumberUtils.isNumber(lastElement)) {
            result = new NumberSymbol(Double.parseDouble(lastElement));
        } else {
            result = new VariableSymbol(lastElement);
        }
        Symbol secondOperand = null;
        while (!stackReservePolishNotation.empty()) {
            lastElement = stackReservePolishNotation.pop();
            if (!isOperator(lastElement)) {
                if (!stackValuesOfVariables.empty()){
                    lastElement = stackValuesOfVariables.pop();
                }
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

    public Symbol getSymbol(String expression) {
        stackValuesOfVariables.clear();
        Symbol result;
        if (isFunction(expression)) {
            String parts[] = {
                    expression.substring(0, 1),
                    expression.replace("(", "").replace(")", "").substring(1)
            };
            if (parts.length > 2)
                return null;
            parse(parts[0]);
            Symbol key = readSymbolFromStack();
            CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
            if (cache.getData().containsKey(key)) {
                FunctionSymbol value = (FunctionSymbol) cache.getData().get(key);
                String argsStr[] = parts[1].split(",");
                List<Symbol> args = new ArrayList<>();
                for (String str : argsStr) {
                    //parse(str);
                    //args.add(readSymbolFromStack());
                    stackValuesOfVariables.push(str);
                }
                Collections.reverse(stackValuesOfVariables);
                parse(value.getExpression());
                result = readSymbolFromStack();
                //result = value.calculate(args);
            } else {
                result = null;
            }
        } else if (expression.contains("=")) {
            String parts[] = expression.split("=");
            if (parts.length > 2)
                return null;
            parse(parts[0]);
            Symbol key = readSymbolFromStack();
            parse(parts[1]);
            Symbol value = readSymbolFromStack();
            if (parts[0].contains("(")) {
                value = new FunctionSymbol(value, parts[1], ((VariableSymbol)key).getName());
            }
            CacheSymbolSingleton.getInstance().addSymbol(key, value);
            result = value;
        } else {
            parse(expression);
            result = readSymbolFromStack();
        }
        return result;
    }
}
