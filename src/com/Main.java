package com;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String expression="1+2+3-4";//2
        Expression evaluator=new Evaluate(expression);
        System.out.println(evaluator.interperetate(evaluator));
    }
}
interface Expression{int interperetate(Expression expression);}
class Number implements Expression {
    int number;

    public Number(int number) {
        this.number = number;
    }

    @Override
    public int interperetate(Expression expression) {
        return number;
    }
}
class Plus implements Expression{
    Expression expressionLeft;
    Expression expressionRight;

    public Plus(Expression expressionLeft, Expression expressionRight) {
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
    }

    @Override
    public int interperetate(Expression context) {
        return expressionLeft.interperetate(context)+expressionRight.interperetate(context);
    }
}

class Minus extends Plus{
    public Minus(Expression expressionLeft, Expression expressionRight) {
        super(expressionLeft,expressionRight);
    }

    @Override
    public int interperetate(Expression context) {
        return expressionLeft.interperetate(context)-expressionRight.interperetate(context);
    }
}
class Evaluate implements Expression{
    Expression evaluate;

    public Evaluate(String expression) {
       Stack<Expression> expressionStack=new Stack<>();
       String expressionStackRevert=new StringBuilder(expression).reverse().toString();
        for (String s:expressionStackRevert.split("\\D")) {
            expressionStack.push(new Number(Integer.parseInt(s)));
        }
        for (String s:expression.split("\\d")) {
            if(s.equals("+")){
                expressionStack.push(new Plus(expressionStack.pop(),expressionStack.pop()));
            }else if(s.equals("-")){
                expressionStack.push(new Minus(expressionStack.pop(),expressionStack.pop()));
            }
        }
        evaluate=expressionStack.pop();
    }

    @Override
    public int interperetate(Expression context) {
        return evaluate.interperetate(context);
    }
}














