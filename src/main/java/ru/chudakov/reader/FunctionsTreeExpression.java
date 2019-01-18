package ru.chudakov.reader;

import org.apache.commons.lang.math.NumberUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.cache.CacheSymbolSingleton;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.*;

import java.util.*;
import java.util.regex.Pattern;

public class FunctionsTreeExpression {
    private List<String> functions;
    private List<String> standardFunctions;
    private List<String> variables;
    private List<String> operators;
    private final String separator = ",";
    private final String openBracket = "(";
    private final String closeBracket = ")";
    private List<String> variablesFunction;
    private List<Symbol> valuesOfVariablesOfFunction;

    private Stack<String> stackOperation = new Stack<>();
    private Stack<String> stackReservePolishNotation = new Stack<>();

    public FunctionsTreeExpression() {
        functions = new ArrayList<>();
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
        operators.add("^");
        standardFunctions = new ArrayList<>();
        standardFunctions.add("first");
        standardFunctions.add("last");
        standardFunctions.add("length");
        standardFunctions.add("while");
        standardFunctions.add("select");
        standardFunctions.add("if");
        variablesFunction = new ArrayList<>();
        valuesOfVariablesOfFunction = new ArrayList<>();
    }

    private boolean isSeparator(@NotNull String token) {
        return token.equals(separator);
    }

    private boolean isOpenBracket(@NotNull String token) {
        return token.equals(openBracket);
    }

    private boolean isCloseBracket(@NotNull String token) {
        return token.equals(closeBracket);
    }

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

    private boolean isStandardFunction(String token) {
        if (isFunction(token)) {
            token = token.substring(0, token.indexOf("("));
            return standardFunctions.contains(token) || functions.contains(token);
        } else {
            return false;
        }
    }

    private boolean isExistFunction(String token) {
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        return cache.getVariableFunction().containsKey(new VariableSymbol(token));
    }

    private boolean isFunction(String token) {
        Pattern pattern = Pattern.compile("\\w+\\(.+(,.+)*\\)");
        return pattern.matcher(token).matches();
    }

    private boolean isNewFunctionOrExpression(String token) {
        return token.contains("=");
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

    private Symbol readSymbolFromStack(boolean useCache) {
        String lastElement = "";
        Stack<Symbol> arguments = new Stack<>();
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        while (!stackReservePolishNotation.empty()) {
            lastElement = stackReservePolishNotation.pop();
            if (isNumberOrVariable(lastElement)) {
                if (variablesFunction.contains(lastElement) && useCache) {
                    arguments.push(
                            valuesOfVariablesOfFunction.get(variablesFunction.indexOf(lastElement))
                    );
                } else if (NumberUtils.isNumber(lastElement)) {
                    arguments.push(new NumberSymbol(Double.parseDouble(lastElement)));
                } else {
                    arguments.push(new VariableSymbol(lastElement));
                }
            } else if (lastElement.equals("+")) {
                arguments.push(arguments.pop().add(arguments.pop()));
            } else if (lastElement.equals("*")) {
                arguments.push(arguments.pop().mul(arguments.pop()));
            } else if (lastElement.equals("-")) {
                arguments.push(arguments.pop().mul(new NumberSymbol(-1d)).add(arguments.pop()));
            } else if (lastElement.equals("/")) {
                arguments.push(arguments.pop().pow(new NumberSymbol(-1d)).mul(arguments.pop()));
            } else if (lastElement.equals("^")) {
                Symbol index = arguments.pop();
                arguments.push(arguments.pop().pow(index));
            } else {
                return null;
            }
        }
        Symbol result = arguments.pop();
        if (cache.getData().containsKey(result) && useCache) {
            result = cache.getData().get(result);
        }
        return result;
    }

    public Symbol getSymbol(String expression) {
        Symbol result = null;
        variablesFunction = new ArrayList<>();
        valuesOfVariablesOfFunction = new ArrayList<>();
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        if (isNewFunctionOrExpression(expression)) {
            String parts[] = expression.split("=");
            if (isFunction(parts[0])) {
                parse(parts[0].substring(0, parts[0].indexOf("(")));
            } else {
                parse(parts[0]);
            }
            Symbol key = readSymbolFromStack(false);
            parse(parts[1]);
            Symbol value = readSymbolFromStack(false);
            if (isFunction(parts[0])) {
                value = new FunctionSymbol(value, parts[1]);
                String[] argsFunction = parts[0]
                        .substring(parts[0].indexOf("("))
                        .replace("(", "")
                        .replace(")", "")
                        .split(",");
                List<String> list = new ArrayList<>();
                for (String s : argsFunction) {
                    list.add(s);
                }
                cache.addVariableFunction(key, list);
            }
            cache.addSymbol(key, value);
            result = value;
        } else if (isStandardFunction(expression)) {
            String name = expression.substring(0, expression.indexOf("("));
            String value = expression
                    .substring(expression.indexOf("("))
                    .replace("(", "")
                    .replace(")", "");
            parse(value);
            result = readSymbolFromStack(true);
            if (name.equals("length")) {
                int length = result.length();
                result = new NumberSymbol(new Double(length));
            } else if (name.equals("first")) {
                result = result.getFirst();
            } else if (name.equals("last")) {
                result = result.getLast();
            } else if (name.equals("select")) {
                String args[] = value.split(",");
                parse(args[0]);
                result = readSymbolFromStack(true);
                parse(args[1]);
                NumberSymbol numberSymbol = (NumberSymbol) readSymbolFromStack(true);
                int index = numberSymbol.getData().intValue();
                if (result.getClass() == SumSymbol.class ||
                result.getClass() == MulSymbol.class) {
                    Symbol branches[] = ((ArithmeticOperationSymbol) result).toArray();
                    if (branches.length > index) {
                        return branches[index];
                    }
                }
            }
        } else if (isFunction(expression)) {
            String args[] = expression
                    .substring(expression.indexOf("("))
                    .replace("(", "")
                    .replace(")", "")
                    .split(",");
            VariableSymbol name = new VariableSymbol(expression.substring(0, expression.indexOf("(")));
            if (isExistFunction(name.getName())) {
                variablesFunction = cache.getVariableFunction().get(name);
            }
            for (String s : args) {
                parse(s);
                valuesOfVariablesOfFunction.add(readSymbolFromStack(true));
            }
            parse(((FunctionSymbol) cache.getData().get(name)).getExpression());
            result = readSymbolFromStack(true);
        } else {
            parse(expression);
            result = readSymbolFromStack(true);
        }
        return result;
    }
}
