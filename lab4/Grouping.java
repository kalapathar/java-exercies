import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***********************************************************************
   Grouping is a subclass of the BorderL Applet that encloses two
   Components within a Panel, thus allowing us to locate both of those
   Components to the "South" of the Applet.

   We will enclose the TextField state variable  userInput  and a new
   Label within the nested Panel.
************************************************************************/

public class Grouping extends BorderL {
  public void init() {
    super.init();

    // userInput already exists in our layout,
    // so we first remove it from our layout.
    cPane.remove(userInput);

    // Now, we create the panel, add the Components to it
    // then add the Panel itself to BorderL's layout.
    JPanel p = new JPanel();
    p.add(new JLabel("Click, then enter text:"));
    p.add(userInput);
    cPane.add(p, BorderLayout.SOUTH);
  }
}