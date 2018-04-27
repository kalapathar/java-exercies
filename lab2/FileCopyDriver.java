/* example file FileCopyDriver.java -- RAB 1/00 */

import java.io.*;

/* Perform file copy using Flanagan's example implementation */

public class FileCopyDriver {
  static final int maxline = 100;

  public static void main(String[] args) {
    byte[] strline = new byte[maxline];
    int strlen;  
    
    System.out.print("Would you like to perform a file copy? (y/n)[y]:  ");
    try {
      strlen = System.in.read(strline, 0, maxline); 
    }
    catch (IOException e) { 
      System.out.println("IOException caught -- some problem with input"); 
      System.out.println(e.getMessage());
      strlen = 0;
    }

    /* The following conversion to String is not necessary---
       we are only doing it to demonstrate String.charAt() method */
    String str = new String(strline, 0, strlen-1);  

    if (strline[0] == 'y' || str.charAt(0) == 'Y') {
      /* First, we read filenames.
	 We could use byte[] buffers and convert to String as before, 
	 but instead we will use a BufferedReader object, which has a 
	 readLine() method for reading input lines into Strings.  
	 Note that System.in is an instance of InputStream.  
	 Stream classes read bytes and Reader classes read characters. */

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      
      try {
	System.out.print("Source filename:  ");
	String fname1 = in.readLine();  // EXCLUDES newline
	
	System.out.print("Destination filename:  ");
	String fname2 = in.readLine();

	FileCopy.copy(fname1, fname2); 
      }
      catch (Exception e) {
	System.err.println("Aborting");
	return; 
      }
      System.out.println("Successful file copy");
    }
  }
}