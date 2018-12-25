package ru.chudakov.symbolic.operation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chudakov.symbolic.Symbol;

@Getter
@Setter
@NoArgsConstructor
public class ExponentSymbol extends FunctionSymbol {
    public ExponentSymbol(Symbol argument) {
        super(argument);
    }
}
