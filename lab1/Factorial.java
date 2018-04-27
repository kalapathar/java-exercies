public class Factorial {
  static long maxarg = 20;

  private static void checkarg(int x) throws IllegalArgumentException {
    if (x < 0) 
      throw new IllegalArgumentException("Factorial:  " + 
					 "argument must be non-negative.");
    if (x > maxarg) 
      throw new IllegalArgumentException("Factorial:  argument " + x +
	 " too large, must not exceed " + maxarg);
    return;
  }

  public static long iterative(int x) throws IllegalArgumentException {
    checkarg(x);
    // assert:  x is a valid argument

    long fact = 1;
    for (int i=2;  i<= x;  i++)
      fact *= i;
    return fact;
  }
  
  public static long recursive(int x) throws IllegalArgumentException {
    checkarg(x);
    // assert:  x is a valid argument

    if (x == 0)
      return 1;
    else
      return x * recursive(x - 1);
  }

  public static long factorial(int x) throws IllegalArgumentException {
    return iterative(x);
  }
}
