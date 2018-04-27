// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

/* Modified by R. Brown 1/8/99:  name change, doc tweak 
   Add a button by subclassing an existing Applet */
   

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Subclass extends Inner {
  JButton clearButton;

  public void init() {
    super.init();  /* call Inner.init() to initialize mouse... */
    this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));

    clearButton = new JButton("Clear");
    clearButton.addActionListener(new MyActionAdapter());
    clearButton.setForeground(Color.black);
    clearButton.setBackground(Color.lightGray);
    this.getContentPane().add(clearButton);
  }


  /*********************************************************************
     MyActionAdapter is an inner class (i.e., local to Subclass) that 
     implements the interface ActionListener for "action" events, such
     as pressing clearButton.  
  **********************************************************************/
  
  class MyActionAdapter implements ActionListener {
    // The method from the ActionListener interface.  Invoked when the 
    // user presses the clearButton 
    public void actionPerformed(ActionEvent e) { // clear the scribble
      getContentPane().repaint();
    }
  }
}
