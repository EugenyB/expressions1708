package ua.mk.berkut.expressions1708.tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyExpression {
    private int id;
    private String expr;
    private double result;
}
