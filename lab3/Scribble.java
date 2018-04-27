// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

/* Modified by R. Brown 1/6/98:  
   Add a button for clearing screen;  added a Choice menu for selecting 
   drawing color, together with color initialization/management code */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Scribble extends JApplet
  implements MouseListener,  MouseMotionListener{
  protected Container cPane;
  private int lastX, lastY;
  
  JComboBox<String> colorChoices;
  String[] colorStrings =
  {"black", "blue", "red", "yellow", "green", "orange", "white","lightGray"};
  Color[] colorVec =
  {Color.black, Color.blue, Color.red, Color.yellow, Color.green, Color.orange, Color.white,Color.lightGray};
  public int colorIndex = 0; // index of the current color.

  public void init() {
    //this.addMouseListener(new myItemAdapter());
    cPane = this.getContentPane();
    cPane.setLayout(new FlowLayout(FlowLayout.LEADING));
    cPane.setBackground(Color.white);

    // Tell this applet what MouseListener and MouseMotionListener
    // objects to notify when mouse and mouse motion events occur.
    // Since we implement the interfaces ourself, our own methods are called.
    cPane.addMouseListener(this);
    cPane.addMouseMotionListener(this);

    

    colorChoices = new JComboBox<String>(colorStrings);
    colorChoices.addItemListener(new MyItemAdapter());
    colorChoices.setForeground(Color.black);
    colorChoices.setBackground(Color.lightGray);
    //    for (int i = 0;  i < colorStrings.length;  i++)
    //      colorChoices.addItem(colorStrings[i]);
    cPane.add(new JLabel(" Color:"));
    cPane.add(colorChoices);
  }

class MyItemAdapter implements ItemListener{
    public void itemStateChanged(ItemEvent e) { // change the color
    colorIndex = colorChoices.getSelectedIndex();    
  } 
}



  // A method from the MouseListener interface.  Invoked when the
  // user presses a mouse button.
  public void mousePressed(MouseEvent e) {
    lastX = e.getX();
    lastY = e.getY();
  }

  // A method from the MouseMotionListener interface.  Invoked when the
  // user drags the mouse with a button pressed.
  public void mouseDragged(MouseEvent e) {
    Graphics g = cPane.getGraphics();
    int x = e.getX(), y = e.getY();
    g.setColor(colorVec[colorIndex]);
    g.drawLine(lastX, lastY, x, y);
    lastX = x; lastY = y;
  }

  // The method from the ActionListener interface.  Invoked when the 
  // user presses the clear button



   

  // The other, unused methods of the MouseListener interface.
  public void mouseReleased(MouseEvent e) {;}
  public void mouseClicked(MouseEvent e) {;}
  public void mouseEntered(MouseEvent e) {;}
  public void mouseExited(MouseEvent e) {;}

  // The other method of the MouseMotionListener interface.
  public void mouseMoved(MouseEvent e) {;}
}
