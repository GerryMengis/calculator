package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class AddOperator extends Operator {

    //addition operator has 1 priority
    @Override
    public int priority() {

        return 1;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {

        //returns a object with value of sum of values of two parameters
        return new Operand(op1.getValue() + op2.getValue());

        //return null;
    }
}
