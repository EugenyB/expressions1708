package ua.mk.berkut.expressions1708.beans;

import lombok.Data;
import ua.mk.berkut.expressions1708.tables.MyExpression;

import java.util.List;

@Data
public class ExpressionBean {
    private String current = "";
    private String message = "";

    private MyExpression expression;

    private List<MyExpression> expressions;
}
