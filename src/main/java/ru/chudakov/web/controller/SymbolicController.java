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
                                                @RequestParam int countSplits) {
        return symbolicService.getPoints(expression, leftBorder, rightBorder, countSplits);
    }
}
