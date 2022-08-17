package ua.mk.berkut.expressions1708.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExpressionService {
    public double calculate(String expressionString) {
        Expression expression = new ExpressionBuilder(expressionString).build();
        return expression.evaluate();
    }
}
