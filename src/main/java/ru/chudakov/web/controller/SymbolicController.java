package ru.chudakov.web.controller;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.chudakov.web.service.SymbolicService;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class SymbolicController {
    private SymbolicService symbolicService;

    @Autowired
    public SymbolicController(SymbolicService symbolicService) {
        this.symbolicService = symbolicService;
    }

    @PostMapping("/getPoints")
    public List<List<List<Double>>> getPoints(@RequestBody String expression) {
        expression = parse(expression);
        return symbolicService.getPoints(expression);
    }

    @PostMapping("/calculate")
    public String calculate(@RequestBody String expression) {
        expression = parse(expression);
        return symbolicService.calculate(expression);
    }

    private String parse(String expression) {
        if (expression.indexOf("=") == expression.length() - 1){
            expression = expression.substring(0, expression.length() - 1);
        }
//        String parts[] = expression.split("&");
//        expression = parts[parts.length - 1];
        expression = expression.replace("%5B", "[");
        expression = expression.replace("%5D", "]");
        expression = expression.replace("%2F", "/");
        expression = expression.replace("%5E", "^");
        expression = expression.replace("%2C", ",");
        expression = expression.replace("%24", "");
        expression = expression.replace("%28", "(");
        expression = expression.replace("%29", ")");
        return expression;
    }
}
