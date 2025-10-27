/**
 * ISBN.java
 * Takes as input the first nine digits of an ISBN-10 number and computes the
 * check digit.
 *
 * Check digit is computed such that the sum of all ten digits, each
 * digit multiplied by its position (i.e., 10 times the first digit, 9 times
 * the second digit, ..., 1 times the last digit) is a multiple of 11.
 * If the check digit is 10, it is represented by 'X'.
 *
 * Example: for the ISBN-10 number 075381675X, the check digit is 'X' because
 * (0*10 + 7*9 + 5*8 + 3*7 + 8*6 + 1*5 + 6*4 + 7*3 + 5*2 + 10*1) = 220,
 * which is a multiple of 11. 
 *
 * Example: for the ISBN-10 number 1540678873, the check digit is '3' because
 * (1*10 + 5*9 + 4*8 + 0*7 + 6*6 + 7*5 + 8*4 + 8*3 + 7*2 + 3*1) = 231,
 * which is a multiple of 11.
 */
public class ISBN 
{
  public static void main(String[] args) 
  {
    // not yet implemented
  }
}
