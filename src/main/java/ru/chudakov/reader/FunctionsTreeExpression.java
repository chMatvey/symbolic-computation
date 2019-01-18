package ru.chudakov.reader;

import org.apache.commons.lang.math.NumberUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.cache.CacheSymbolSingleton;
import ru.chudakov.symbolic.operand.NumberSymbol;
import ru.chudakov.symbolic.operand.VariableSymbol;
import ru.chudakov.symbolic.operation.FunctionSymbol;

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
    private List<String> valuesOfVariablesOfFunction;

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
        standardFunctions.add("if");
        variablesFunction = new ArrayList<>();
        valuesOfVariablesOfFunction = new ArrayList<>();
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
//        Pattern pattern = Pattern.compile("\\w+\\(\\w+(,\\w+)*\\)");
//        return pattern.matcher(token).matches();
        return functions.contains(token) || standardFunctions.contains(token) ||
                CacheSymbolSingleton.getInstance().getData().containsKey(new VariableSymbol(token));
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
        Stack<Symbol> arguments = new Stack<>();
        Stack<String> argumentsString = new Stack<>();
        String lastElement = "";
        if (isFunction(stackReservePolishNotation.lastElement())) {
            Collections.reverse(stackReservePolishNotation);
        }
        while (!stackReservePolishNotation.empty()) {
            lastElement = stackReservePolishNotation.pop();
            if (!isOperator(lastElement)&&!isFunction(lastElement)) {
                if (variablesFunction.contains(lastElement)) {
                    lastElement = valuesOfVariablesOfFunction.get(
                            variablesFunction.indexOf(lastElement)
                    );
                }
                //thirdArgument = secondArgument;
                //secondArgument = firstArgument;
                if (NumberUtils.isNumber(lastElement)) {
                    arguments.push(new NumberSymbol(Double.parseDouble(lastElement)));
                    //firstArgument = new NumberSymbol(Double.parseDouble(lastElement));
                } else {
                    arguments.push(new VariableSymbol(lastElement));
                    //firstArgument = new VariableSymbol(lastElement);
                }
                argumentsString.push(lastElement);
            } else if (lastElement.equals("+")) {
                arguments.push(arguments.pop().add(arguments.pop()));
                argumentsString.push(argumentsString.pop() + "+" + argumentsString.pop());
                //firstArgument = firstArgument.add(secondArgument);
            } else if (lastElement.equals("-")) {
                arguments.push(arguments.pop().mul(new NumberSymbol(-1d)).add(arguments.pop()));
                argumentsString.push(argumentsString.pop() + "-" + argumentsString.pop());
                //firstArgument = secondArgument.add(firstArgument.mul(new NumberSymbol(-1d)));
            } else if (lastElement.equals("*")) {
                arguments.push(arguments.pop().mul(arguments.pop()));
                argumentsString.push(argumentsString.pop() + "*" + argumentsString.pop());
                //firstArgument = firstArgument.mul(secondArgument);
            } else if (isFunction(lastElement)) {
                valuesOfVariablesOfFunction = new ArrayList<>(argumentsString);
                CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
                if (cache.getData().containsKey(new VariableSymbol(lastElement))) {
                    variablesFunction = cache.getVariableFunction()
                            .get(new VariableSymbol(lastElement));
                }
                return calculateFunction(lastElement);
                //secondArgument = thirdArgument;
            } else {
                return null;
            }
        }
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        return arguments.pop();
        //return firstArgument;
    }

    private Symbol calculateFunction(String functionType) {
        if (functionType.equals("length")) {
            parse(valuesOfVariablesOfFunction.get(0));
            Symbol symbol = readSymbolFromStack();
            int length = symbol.length();
            return new NumberSymbol(new Double(length));
        } else if (functionType.equals("first")) {
            parse(valuesOfVariablesOfFunction.get(0));
            return readSymbolFromStack().getFirst();
        } else if (functionType.equals("last")) {
            parse(valuesOfVariablesOfFunction.get(0));
            return readSymbolFromStack().getLast();
        } else if (functionType.equals("while")) {
            return null;
        } else if (functionType.equals("if")) {
            return null;
        } else if (CacheSymbolSingleton.getInstance().getData().containsKey(new VariableSymbol(functionType))){
            CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
            FunctionSymbol functionSymbol = (FunctionSymbol) cache.getData()
                    .get(new VariableSymbol(functionType));
            parse(functionSymbol.getExpression());
            return readSymbolFromStack();
        } else {
            return null;
        }
    }

    public Symbol getSymbol(String expression) {
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        variablesFunction = new ArrayList<>();
        valuesOfVariablesOfFunction= new ArrayList<>();
        Symbol result;
//        if (isFunction(expression)) {
//            String parts[] = {
//                    expression.substring(0, 1),
//                    expression.replace("(", "").replace(")", "").substring(1)
//            };
//            if (parts.length > 2)
//                return null;
//            parse(parts[0]);
//            Symbol key = readSymbolFromStack();
//            CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
//            if (cache.getData().containsKey(key)) {
//                FunctionSymbol value = (FunctionSymbol) cache.getData().get(key);
//                variablesFunction = new ArrayList<>(
//                        cache.getVariableFunction().get(key)
//                );
//                String str[] = parts[1].split(",");
//                for (String s : str) {
//                    valuesOfVariablesOfFunction.add(s);
//                }
//                if (variablesFunction.size() != valuesOfVariablesOfFunction.size()) {
//                    return null;
//                }
//                parse(value.getExpression());
//                result = readSymbolFromStack();
//            } else {
//                result = null;
//            }
//        }
        if (expression.contains("=")) {
            String parts[] = expression.split("=");
            if (parts.length > 2)
                return null;
            if (parts[0].contains("(")) {
                parts[0] = parts[0].substring(0, parts[0].indexOf("("));
            }
            parse(parts[0]);
            Collections.reverse(stackReservePolishNotation);
            Symbol key = readSymbolFromStack();
            parse(parts[1]);
            Symbol value = readSymbolFromStack();
            if (expression.contains("(")) {
                value = new FunctionSymbol(value, parts[1]);
                String strArgs = expression.split("=")[0];
                String args[] = strArgs
                        .replace("(", "")
                        .replace(")", "")
                        .substring(1)
                        .split(",");
                List<String> list = new ArrayList<>();
                for (String s : args) {
                    list.add(s);
                }
                //CacheSymbolSingleton.getInstance().getData().remove(key);
                CacheSymbolSingleton.getInstance().addVariableFunction(key, list);
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
