import java.io.*;

public class FactorialDriver {
  static final int maxline = 100;
  public static void main(String[] args) {
    byte[] buff = new byte[maxline];  /* input buffer */
    int len = 0;  /* number of bytes most recently read into buff */
    
    for (;;) {
      String str;  /* holds string equivalent of buff */
      int val = 0;  /* to hold value of input integer read into buff */

      System.out.print("Enter a number, or type `quit': ");
      try { 
	len = System.in.read(buff, 0, maxline); 
      }
      catch (IOException e) { 
	System.out.println("IOException caught -- some problem with input"); 
	System.out.println(e.getMessage());
	continue;  /* ADDED 2/05:  restart loop from beginning! */
      }
      str = new String(buff, 0, len-1);  /* excludes terminating newline */
      if (str.equals("quit") || str.equals("`quit'")) {
	System.out.println("Goodbye.");
	break;  /* leave enclosing for loop */
      }
      /* assert:  exit was not requested */
      
      try {
	val = Integer.parseInt(new String(buff, 0, len-1));
	System.out.println(val + "! = " + Factorial.iterative(val));
      }
      catch (NumberFormatException e) {
	System.out.println("NumberFormatException caught:");
	System.out.println(e.getMessage());
	System.out.println("Please try again.");
      }
      catch (IllegalArgumentException e) {
	System.out.println("IllegalArgumentException caught:");
	System.out.println(e.getMessage());
	System.out.println("Please try again.");
      }
      System.out.println();
    }
  }
}