// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

/* Modified by R. Brown 1/99:  name change, doc tweak 
   Use inner classes for adapters to handle mouse events 
   1/00:  fix naming conventions, make state variables for adapters
   to facilitate subclassing */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Scrib extends JApplet {
  protected Container cPane;
  
  /* state variables */
  int lastX, lastY;
  // We will define the following mouse-related adapters as state variables
  // so that subclasses of Scrib can override them, e.g., to draw in color
  MouseListener mouseAdapt;
  MouseMotionListener mouseMotionAdapt;

  public void init() {
    mouseAdapt = new MyMouseAdapter();
    this.addMouseListener(mouseAdapt);

    mouseMotionAdapt = new MyMouseMotionAdapter();
    this.addMouseMotionListener(mouseMotionAdapt);

    cPane = this.getContentPane();
    cPane.setBackground(Color.white);

    cPane.setLayout(new FlowLayout(FlowLayout.LEADING));//default: BorderLayout
  }

  class MyMouseAdapter extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      lastX = e.getX(); // NOTE: this is Scrib.lastX, by locality
      lastY = e.getY();
    }
  }

  class MyMouseMotionAdapter implements MouseMotionListener {
    public void mouseDragged(MouseEvent e) {
      int x = e.getX(), y = e.getY();
      Graphics g = getGraphics();  // get a Graphics for Scrib
      g.drawLine(lastX, lastY, x, y);
      lastX = x; lastY = y;
    }

    // The other method of the MouseMotionListener interface.
    public void mouseMoved(MouseEvent e) {;}
  }
}
