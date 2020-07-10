package edu.csc413.calculator.evaluator;
/**
 * Operand class used to represent an operand
 * in a valid mathmetical expression.
 */
public class Operand {
  /**
  * construct operand from string token.
  */
  //this value represents the value of this operand
  private int value;

  public Operand( String token ) { value = Integer.parseInt(token);}
  /**
   * construct operand from integer
   */
  public Operand( int value ) { this.value = value;}
  /**
   * return value of opernad
   */
  public int getValue() { return value;   }
   /**
   * Check to see if given token is a valid
   * operand.
   */
  public static boolean check( String token ) {

  //returns true if token is 0-9
    return Character.isDigit(token.charAt(0));
  }
}
