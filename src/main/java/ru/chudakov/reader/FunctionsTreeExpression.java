package ru.chudakov.reader;

import javafx.util.Pair;
import org.apache.commons.lang.math.NumberUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.chudakov.symbolic.Symbol;
import ru.chudakov.symbolic.cache.CacheSymbolSingleton;
import ru.chudakov.symbolic.operand.*;
import ru.chudakov.symbolic.operation.*;

import java.util.*;
import java.util.regex.Pattern;

public class FunctionsTreeExpression {
    private List<String> operators;
    private final String separator = ",";
    private final String openBracket = "(";
    private final String closeBracket = ")";
    private List<String> standardFunctions;

    public FunctionsTreeExpression() {
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
        standardFunctions.add("select");
        standardFunctions.add("while");
        standardFunctions.add("if");
        standardFunctions.add("delayed");
        standardFunctions.add("less");
        standardFunctions.add("sin");
        standardFunctions.add("getPoints");
        //variablesFunction = new ArrayList<>();
        //valuesOfVariablesOfFunction = new ArrayList<>();
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

    private boolean isOperator(@NotNull String token) {
        return operators.contains(token);
    }

    private boolean isNumberOrVariable(@NotNull String token) {
        Pattern pattern = Pattern.compile("[\\w|.]+");
        return pattern.matcher(token).matches() || NumberUtils.isNumber(token);
    }

    private byte getPrecedence(@NotNull String token) {
        if (token.equals("+") || token.equals("-"))
            return 1;
        else if (token.equals("*") || token.equals("/")) {
            return 2;
        }
        return 3; //^//
    }

    private boolean isFunction(@NotNull String token) {
        Pattern pattern = Pattern.compile("\\w+\\(.+(,.+)*\\)");
        return pattern.matcher(token).matches();
    }

    private boolean isNewFunctionOrExpression(@NotNull String token) {
        return token.contains("=");
    }

    private boolean isStandardFunction(@NotNull String token) {
        return standardFunctions.contains(token);
    }

    private boolean isUserFunction(@NotNull String token) {
        return CacheSymbolSingleton.getInstance().getFunctions().containsKey(token);
    }

    private Stack<String> parse(@NotNull String expression) {
        Stack<String> stackOperation = new Stack<>();
        Stack<String> stackReservePolishNotation = new Stack<>();
        expression = expression
                .replace(" ", "")
                .replace(",-", ",0-")
                .replace("(-", "(0-")
                .replace("[-", "[0-")
                .replace("_", ".");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(
                expression,
                operators + separator + openBracket + closeBracket + "[" + "]" + ";",
                true
        );
        String token;
        while (stringTokenizer.hasMoreTokens()) {
            token = stringTokenizer.nextToken();
            if (isStandardFunction(token) || isUserFunction(token)) {
                stackOperation.push(token);
            } else if (isNumberOrVariable(token)) {
                stackReservePolishNotation.push(token);
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
                if (!stackOperation.empty() && (
                        isStandardFunction(stackOperation.lastElement()) || isUserFunction(stackOperation.lastElement())
                )) {
                    stackReservePolishNotation.push(stackOperation.pop());
                }
            } else if (token.equals("[")) {
                stackReservePolishNotation.push(token);
            } else if (token.equals("]")) {
                while (!stackOperation.empty()) {
                    if (isOperator(stackOperation.lastElement())) {
                        stackReservePolishNotation.push(stackOperation.pop());
                    } else {
                        break;
                    }
                }
                stackReservePolishNotation.push(token);
            } else if (token.contains(";")) {
                while (!stackOperation.empty()) {
                    stackReservePolishNotation.push(stackOperation.pop());
                }
                stackReservePolishNotation.push(";");
            } else {
                stackReservePolishNotation.clear();
                return null;
            }
        }
        while (!stackOperation.empty()) {
            stackReservePolishNotation.push(stackOperation.pop());
        }
        System.out.println(stackReservePolishNotation);
        Collections.reverse(stackReservePolishNotation);
        return stackReservePolishNotation;
    }

    private Symbol readSymbolFromStack(@NotNull Stack<String> stackReservePolishNotation,
                                       @NotNull List<String> variablesFunction,
                                       @NotNull List<Symbol> valuesOfVariablesOfFunction,
                                       @NotNull List<Pair<String, String>> valueFunctionVariables,
                                       boolean useCache) {
        String lastElement;
        Stack<Symbol> arguments = new Stack<>();
        List<Symbol> listForListSymbol = new ArrayList<>();
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        while (!stackReservePolishNotation.empty()) {
            lastElement = stackReservePolishNotation.pop();
            if (isOperator(lastElement)) {
                Symbol result;
                if (arguments.empty()) {
                    return null;
                } else if (lastElement.equals("+")) {
                    result = arguments.pop().add(arguments.pop());
                } else if (lastElement.equals("*")) {
                    result = arguments.pop().mul(arguments.pop());
                } else if (lastElement.equals("-")) {
                    result = arguments.pop().mul(new NumberSymbol(-1d)).add(arguments.pop());
                } else if (lastElement.equals("/")) {
                    result = arguments.pop().pow(new NumberSymbol(-1d)).mul(arguments.pop());
                } else if (lastElement.equals("^")) {
                    Symbol index = arguments.pop();
                    result = arguments.pop().pow(index);
                } else {
                    return null;
                }
                if (cache.getVariablesAndFunction().containsKey(result)) {
                    result = cache.getVariablesAndFunction().get(result);
                }
                arguments.push(result);
            } else if (isStandardFunction(lastElement)) {
                if (arguments.empty()) {
                    return null;
                }
                if (lastElement.equals("first")) {
                    arguments.push(arguments.pop().getFirst());
                } else if (lastElement.equals("last")) {
                    arguments.push(arguments.pop().getLast());
                } else if (lastElement.equals("length")) {
                    arguments.push(new NumberSymbol((double) arguments.pop().length()));
                } else if (lastElement.equals("select")) {
                    Symbol firstArgument = arguments.pop();
                    if (firstArgument.getClass() != NumberSymbol.class && arguments.empty()) {
                        return null;
                    }
                    Symbol secondArgument = arguments.pop();
                    int index = ((NumberSymbol) firstArgument).getData().intValue();
                    if (secondArgument.getClass() == SumSymbol.class || secondArgument.getClass() == MulSymbol.class) {
                        Symbol branches[] = ((ArithmeticOperationSymbol) secondArgument).toArray();
                        if (branches.length > index) {
                            arguments.push(branches[index]);
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else if (lastElement.equals("delayed")) {
                    arguments.pop();
                    Symbol functionName = arguments.pop();
                    if (functionName.getClass() != VariableSymbol.class) {
                        return null;
                    }
                    String expression = cache.getExpression();
                    expression = expression.substring(expression.indexOf("("));
                    String parts[] = expression.split(",");
                    String name = parts[0].substring(1);
                    String value = parts[1].substring(0, parts[1].length() - 1);
                    arguments.push(getFunctionOrVariable(name + "=" + value));
                } else if (lastElement.equals("less")) {
                    Symbol right = arguments.pop();
                    if (arguments.empty()) {
                        return null;
                    }
                    Symbol left = arguments.pop();
                    if (left.getClass() != NumberSymbol.class || right.getClass() != NumberSymbol.class) {
                        return null;
                    }
                    NumberSymbol difference = (NumberSymbol) left.add(right.mul(new NumberSymbol(-1d)));
                    if (difference.getData() < 0) {
                        arguments.push(new NumberSymbol(1d));
                    } else {
                        arguments.push(new NumberSymbol(0d));
                    }
                } else if (lastElement.equals("if")) {
                    if (arguments.size() < 3) {
                        return null;
                    }
                    Symbol elseBody = arguments.pop();
                    Symbol body = arguments.pop();
                    if (arguments.lastElement().getClass() != NumberSymbol.class) {
                        return null;
                    }
                    NumberSymbol condition = (NumberSymbol) arguments.pop();
                    if (condition.getData() == 1d) {
                        arguments.push(body);
                    } else {
                        arguments.push(elseBody);
                    }
                } else if (lastElement.equals("while")) {
                    if (arguments.size() < 2) {
                        return null;
                    }
                    Symbol body = arguments.pop();
                    if (arguments.lastElement().getClass() != NumberSymbol.class) {
                        return null;
                    }
                    int condition = ((NumberSymbol) arguments.pop()).getData().intValue();
                    while (condition == 1) {
                        arguments.push(body);
                        condition = ((NumberSymbol) arguments.pop()).getData().intValue();
                    }
                } else if (lastElement.equals("sin")) {
                    if (arguments.empty()) {
                        return null;
                    }
                    Symbol argument = arguments.pop();
                    if (argument.getClass() == NumberSymbol.class || argument.getClass() == FractionSymbol.class) {
                        arguments.push(
                                new NumberSymbol(Math.sin(((NumberSymbol) argument).getData()))
                        );
                    } else {
                        arguments.push(new SinFunctionSymbol(argument));
                    }
                } else if (lastElement.equals("getPoints")) {
                    Symbol countSplitsSymbol = arguments.pop();
                    Symbol rightBorderSymbol = arguments.pop();
                    Symbol leftBorderSymbol = arguments.pop();
                    Symbol expressionSymbol = arguments.pop();
                    if (countSplitsSymbol.getClass() != NumberSymbol.class ||
                            rightBorderSymbol.getClass() != NumberSymbol.class ||
                            leftBorderSymbol.getClass() != NumberSymbol.class) {
                        return null;
                    }
                    int countSplits = ((NumberSymbol) countSplitsSymbol).getData().intValue();
                    double leftBorder = ((NumberSymbol) leftBorderSymbol).getData();
                    double rightBorder = ((NumberSymbol) rightBorderSymbol).getData();
                    List<Symbol> result = new ArrayList<>();
                    if (expressionSymbol.getClass() != ListSymbol.class) {
                        result.add(getPoints(expressionSymbol, leftBorder, rightBorder, countSplits));
                    } else {
                        ListSymbol expressionListSymbol = (ListSymbol) expressionSymbol;
                        for (Symbol symbol : expressionListSymbol.getList()) {
                            result.add(getPoints(symbol, leftBorder, rightBorder, countSplits));
                        }
                    }
                    arguments.push(new ListSymbol(result));
                } else if (lastElement.equals("plot")) {
                    if (arguments.lastElement().getClass() != ListSymbol.class) {
                        return null;
                    }
                } else {
                    return null;
                }
            } else if (isUserFunction(lastElement) && useCache) {
                String functionExpression = cache.getFunctions().get(lastElement);
                List<String> newVariablesFunction = cache.getVariableFunction().get(lastElement);
                List<Symbol> newValuesOfVariablesOfFunction = new ArrayList<>();
                for (int i = 0; i < newVariablesFunction.size(); i++) {
                    if (arguments.empty()) {
                        return null;
                    }
                    newValuesOfVariablesOfFunction.add(arguments.pop());
                }
                Stack<String> stack = parse(functionExpression);
                if (stack == null || newValuesOfVariablesOfFunction.size() != newVariablesFunction.size()) {
                    return null;
                }
                List<Pair<String, String>> pairs = cache.getValueVariableFunction().get(lastElement);
                Symbol result = readSymbolFromStack(stack, newVariablesFunction, newValuesOfVariablesOfFunction,
                        pairs, true);
                if (result == null) {
                    return null;
                }
                arguments.push(result);
            } else if (isNumberOrVariable(lastElement)) {
                Symbol result;
                if (variablesFunction.contains(lastElement)) {
                    result = valuesOfVariablesOfFunction.get(variablesFunction.indexOf(lastElement));
                } else if (NumberUtils.isNumber(lastElement)) {
                    result = new NumberSymbol(Double.parseDouble(lastElement));
                } else {
                    result = new VariableSymbol(lastElement);
                    if (cache.getVariablesAndFunction().containsKey(result) && useCache) {
                        result = cache.getVariablesAndFunction().get(result);
                    } else if (!valueFunctionVariables.isEmpty()) {
                        for (Pair<String, String> pair : valueFunctionVariables) {
                            if (pair.getKey().equals(lastElement)) {
                                Stack<String> reservePolishNotationStack = parse(pair.getValue());
                                ;
                                if (reservePolishNotationStack == null) {
                                    return null;
                                }
                                result = readSymbolFromStack(
                                        reservePolishNotationStack,
                                        variablesFunction,
                                        valuesOfVariablesOfFunction,
                                        valueFunctionVariables,
                                        true
                                );
                                break;
                            }
                        }
                    } else {
                        result = new VariableSymbol(lastElement);
                    }
                }
                arguments.push(result);
            } else if (lastElement.equals("[")) {
                arguments.push(new EmptySymbol());
            } else if (lastElement.equals("]")) {
                List<Symbol> list = new ArrayList<>();
                while (arguments.lastElement().getClass() != EmptySymbol.class) {
                    list.add(arguments.pop());
                }
                Collections.reverse(list);
                arguments.pop();
                arguments.push(new ListSymbol(list));
            } else if (lastElement.equals(";")) {
                listForListSymbol.add(arguments.pop());
            } else {
                return null;
            }
        }
        if (listForListSymbol.isEmpty()) {
            return arguments.pop();
        } else {
            listForListSymbol.add(arguments.pop());
            return new ListSymbol(listForListSymbol);
        }
    }

    private ListSymbol getPoints(Symbol expressionSymbol, double leftBorder, double rightBorder,
                                 int countSplits) {
        List<Symbol> listForCreateListSymbol = new ArrayList<>();
        TreeMap<VariableSymbol, NumberSymbol> values = new TreeMap<>();
        Symbol symbol = getVariable(expressionSymbol);
        double delta = (rightBorder - leftBorder) / countSplits;
        if (symbol == null || symbol.getClass() != VariableSymbol.class) {
            return null;
        }
        VariableSymbol variableSymbol = (VariableSymbol) symbol;
        values.put(variableSymbol, new NumberSymbol(leftBorder));
        List<Symbol> pair;
        Symbol valueUserFunction;
        for (int i = 0; i <= countSplits; i++) {
            pair = new ArrayList<>();
            valueUserFunction = expressionSymbol.putValue(values);
            if (valueUserFunction.getClass() != NumberSymbol.class) {
                return null;
            }
            pair.add(new NumberSymbol(leftBorder));
            pair.add(valueUserFunction);
            leftBorder += delta;
            listForCreateListSymbol.add(new ListSymbol(pair));
            values.clear();
            values.put(variableSymbol, new NumberSymbol(leftBorder));
        }
        return new ListSymbol(listForCreateListSymbol);
    }

    private VariableSymbol getVariable(Symbol symbol) {
        if (symbol.getClass() == NumberSymbol.class) {
            return null;
        } else if (symbol.getClass() == VariableSymbol.class) {
            return (VariableSymbol) symbol;
        } else if (symbol.getClass() == SumSymbol.class || symbol.getClass() == MulSymbol.class) {
            Symbol variable = new EmptySymbol();
            ArithmeticOperationSymbol sumSymbol = (ArithmeticOperationSymbol) symbol;
            for (Symbol branch : sumSymbol.getBranches()) {
                Symbol result = getVariable(branch);
                if (result == null) {
                    continue;
                } else if (result.getClass() == VariableSymbol.class) {
                    return (VariableSymbol) result;
                }
            }
            return null;
        } else if (symbol.getClass() == PowerSymbol.class) {
            PowerSymbol powerSymbol = (PowerSymbol) symbol;
            Symbol result = getVariable(powerSymbol.getBase());
            if (result == null) {
                result = new EmptySymbol();
            }
            if (result.getClass() != VariableSymbol.class) {
                result = getVariable(powerSymbol.getIndex());
            }
            if (result == null) {
                result = new EmptySymbol();
            }
            if (result.getClass() != VariableSymbol.class) {
                return null;
            } else {
                return (VariableSymbol) result;
            }
        } else {
            return null;
        }
    }

    private Symbol getFunctionOrVariable(String expression) {
        Stack<String> stackReservePolishNotation;
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        String parts[] = expression.split("=");
        for (int i = 2; i < parts.length; i++) {
            parts[1] = parts[1] + "=" + parts[i];
        }
        String functionValue = parts[1];
        String name;
        if (isFunction(parts[0])) {
            name = parts[0].substring(0, parts[0].indexOf("("));
        } else {
            name = parts[0];
        }
        if (NumberUtils.isNumber(name) || !isNumberOrVariable(name)) {
            return null;
        }
        stackReservePolishNotation = parse(name);
        if (stackReservePolishNotation == null) {
            return null;
        }
        Symbol key = readSymbolFromStack(stackReservePolishNotation, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), false);
        Symbol value;
        if (isFunction(parts[0])) {
            String args[] = parts[0]
                    .substring(parts[0].indexOf("("))
                    .replace("(", "")
                    .replace(")", "")
                    .split(",");
            Set<String> set = new TreeSet<>(Arrays.asList(args));
            List<String> list = new ArrayList<>(set);
//            if (list.contains(name)) {
//                return null;
//            }
            List<Pair<String, String>> listValueVariableFunction = new ArrayList<>();
            if (functionValue.contains(";")) {
                String functionValueAndVariable[] = parts[1].split(";");
                functionValue = functionValueAndVariable[0];
                for (int i = 1; i < functionValueAndVariable.length; i++) {
                    String nameAndValueVariableFunction[] = functionValueAndVariable[i].split("=");
                    String nameFunctionVariable = nameAndValueVariableFunction[0];
                    String valueFunctionVariable = nameAndValueVariableFunction[1];
                    listValueVariableFunction.add(new Pair<>(
                            nameFunctionVariable,
                            valueFunctionVariable
                    ));
                }
            }
            stackReservePolishNotation = parse(functionValue);
            if (stackReservePolishNotation == null) {
                return null;
            }
            value = readSymbolFromStack(stackReservePolishNotation, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(), true);
            value = new FunctionSymbol(value);
            cache.addFunction(name, functionValue, key, value, list, listValueVariableFunction);
        } else {
//            if (parts[1].contains(name)) {
//                return null;
//            }
            stackReservePolishNotation = parse(functionValue);
            if (stackReservePolishNotation == null) {
                return null;
            }
            value = readSymbolFromStack(stackReservePolishNotation, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(), true);
            cache.addVariable(name, key, value);
        }
        return value;
    }

    public Symbol getSymbol(String expression) {
        Stack<String> stackReservePolishNotation;
        CacheSymbolSingleton cache = CacheSymbolSingleton.getInstance();
        cache.setExpression(expression);
        if (isNewFunctionOrExpression(expression)) {
            return getFunctionOrVariable(expression);
        } else {
            stackReservePolishNotation = parse(expression);
            if (stackReservePolishNotation == null) {
                return null;
            }
            return readSymbolFromStack(stackReservePolishNotation, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(), true);
        }
    }

    public TreeSet<VariableSymbol> getVariables(String expression) {
        Stack<String> reservePolishNotationStack = parse(expression);
        if (reservePolishNotationStack == null) {
            return null;
        }
        String lastElement;
        TreeSet<VariableSymbol> result = new TreeSet<>();
        while (!reservePolishNotationStack.empty()) {
            lastElement = reservePolishNotationStack.lastElement();
            if (isNumberOrVariable(lastElement) && !NumberUtils.isNumber(lastElement)) {
                result.add(new VariableSymbol(lastElement));
            }
            reservePolishNotationStack.pop();
        }
        return result;
    }
}
