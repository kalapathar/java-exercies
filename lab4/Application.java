/* Standalone application that launches an applet from within a Frame.  
   NOTE:  Do not use appletviewer or a browser, since this isn't itself an 
   applet!  Instead, compile then use JVM directly:  % java Application
   RAB 1/12/98 */ 

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Application {
  public static void main(String[] args) {
    JFrame f = new JFrame();  
    Scribble a = new Scribble();  
    f.getContentPane().add(a, BorderLayout.CENTER);
    f.setSize(500, 300);
    //    f.pack();
    a.init();
    f.setVisible(true);
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) { System.exit(0); }
    });
  }
}