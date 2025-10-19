/**
 * Binary.java
 * Converts a decimal integer to its binary representation and prints it.
 * Example: input 5 -> output 101
 * Example: input 10 -> output 1010
 */
public class Binary
{
  public static void main(String[] args)
  {
    int n = Integer.parseInt(args[0]);
    String s = "";
    for (int i = n / 2; i > 0; i /= 2) {
      s = s + (i % 2);
    }
    System.out.println(s);
  }
}
