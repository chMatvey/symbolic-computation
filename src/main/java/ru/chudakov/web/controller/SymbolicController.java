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
    public List<List<Double>> getPoints(@RequestBody String expression,
                                        @RequestParam Double leftBorder,
                                        @RequestParam Double rightBorder,
                                        @RequestParam Double delta) {
        expression = expression.substring(0, expression.length() - 1);
        String parts[] = expression.split("&");
        expression = parts[parts.length - 1];
        expression = expression.replace("%2F", "/");
        expression = expression.replace("%5E", "^");
        return symbolicService.getPoints(expression, leftBorder, rightBorder, delta);
    }
}
