import java.io.*;
import java.nio.charset.*;
import java.util.stream.*;

/** A class fulfilling the Message class specs
 * @author Deepak Shah */

public class Message {
  // class variables
  /** terminates a part of a message */
  public static final String TERMINATOR = ",";
  /** buffer size */
  public static final int MAXBUFF = 100000;
  /** indicates the role of a message in a protocol */
  String type;
  /** indicates the data in a message */
  String content;
  /** Initializes an instance of <code>Message</code>, using 2 String arguments.
  @param str1 The first string argument which sets the value of type
  @param str2 The second string argument which sets the value of content */
  public Message(String str1, String str2) {
    type = str1;
    content = str2;
  } 

  /** Initializes an instance of <code>Message</code>, using a InputStream.
  @param arg An argument of type InputStream through which we set the values for type and content.
  @exception IOException
    Thrown if <code>InputStream arg</code> is not a valid input stream
  @exception EOFException
    Thrown if <code>InputStream arg</code> has been reached unexpectedly during input
   */
  public Message(InputStream arg) throws IOException, EOFException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(arg, "UTF-8"))) {
      String input = br.lines().collect(Collectors.joining(System.lineSeparator()));
      //System.out.println("This is the input:  "+input);
      String[] inputarray = input.split(",");
      type = inputarray[0];
      content = inputarray[1];
      //System.out.println(type);
      //System.out.println(content);
    }
  } 

  public void updateMessage(String str1, String str2) {
    type = str1;
    content = str2;
  }

  public void updateMessage(InputStream arg) throws IOException, EOFException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(arg, "UTF-8"))) {
      String input = br.lines().collect(Collectors.joining(System.lineSeparator()));
      String[] inputarray = input.split(",");
      type = inputarray[0];
      content = inputarray[1];
    }
  }

  /** Produce a byte-array representation of this Message
  @return Byte array that expresses this Message, using UTF-8 encoding
  @exception UnsupportedEncodingException
    Thrown if <code>type</code> cannot be converted to a byte array.
   */
  public byte[] getBytes() throws UnsupportedEncodingException {
    String str = type + "," + content;
    System.out.println("getBytes()");
    System.out.println(str);
    byte[] strb = str.getBytes(StandardCharsets.UTF_8);
    return strb;
  }

  /** @param args Array is initialized using the command line arguments the user passes to the program when they run it
  @exception IOException
    Thrown if the file "input-text.txt" cannot be read in as a <code>InputStream</code>
   */
  public static void main(String[] args) throws IOException {
    InputStream IS = new FileInputStream("input-text.txt");
    Message mess = new Message(IS);
  }

  /** @param arg Argument passed as <code>OutputStream</code> which is written into as output
  @exception IOException
    Thrown if the file <code>OutputStream arg</code> cannot be written into
   */
  public void send(OutputStream arg) throws IOException {
    arg.write(getBytes());
    arg.flush();
  }

  /** @return Printable representation of this message
   */
  @Override
  public String toString() {
    return "The type of Message is " + type + "\nThe content of this Message is " + content;
  }

  public String getContent() {
    return content;
  }

  public String getType() {
    return type;
  }

}