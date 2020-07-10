package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/( )";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval(String expression )
  {
    String token;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    //expression is String;
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators


    //hasMoreTokens means "Tests if there are more tokens available from this tokenizer's string."
    while ( this.tokenizer.hasMoreTokens() )
    {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " ))
      {
        // check if token is an operand
        if ( Operand.check( token ))
        {
          operandStack.push( new Operand( token ));
        }
        else {
          if ( ! Operator.check( token )) {//it could be a symbol or Char A-Z
            System.out.println( "*****invalid token******" );
            throw new RuntimeException("*****invalid token******");
          }

          Operator newOperator = Operator.operators.get(token);

            //if operator is openBracket simply add "(" to the operator stack
            if(token.equals("("))
            {
                operatorStack.push(Operator.getOperator("("));
                continue;
            }

            //else if operator is closeBracketOperator
            else if(token.equals(")"))
            {
                //do the calculations until the closeBracketOperator appears.
                while (!(operatorStack.peek().priority() == 0))
                {
                    Operator oldOpr = operatorStack.pop();
                    Operand op2 = operandStack.pop();
                    Operand op1 = operandStack.pop();
                    operandStack.push(oldOpr.execute(op1, op2));
                }
                // When priority 0 appears, remove the closeBracketOperator from the operatorStack.
                operatorStack.pop();
                continue;
            }
            else

                while ( !(operatorStack.isEmpty()) && operatorStack.peek().priority() >= newOperator.priority() )
                {
                    //check the operandstack based on priority order,
                    // peek operand and execute the result
                    //push the operand result back to operandstack
                    Operator oldOpr = operatorStack.pop();
                    Operand op2 = operandStack.pop();
                    Operand op1 = operandStack.pop();
                    operandStack.push( oldOpr.execute( op1, op2 ));
                }
                operatorStack.push( newOperator );
        }

      }

    }

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks (except
    // the init operator on the operator stack); that is, we should keep
    // evaluating the operator stack until it only contains the init operator;
    // Suggestion: create a method that takes an operator as argument and
    // then executes the while loop.

      while (!operatorStack.isEmpty())
      {
        Operator oldOpr = operatorStack.pop();
        Operand op2 = operandStack.pop();
        Operand op1 = operandStack.pop();
        operandStack.push(oldOpr.execute(op1, op2));
      }
      //returns the result
      return operandStack.pop().getValue();
  }


}
