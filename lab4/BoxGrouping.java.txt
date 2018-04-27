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

public class BoxGrouping extends BorderL {
  public void init() {
    super.init();

    // userInput already exists in our layout,
    // so we first remove it from our layout.
    cPane.remove(userInput);
    userInput.setMaximumSize(new Dimension(50, 25));

    // Now, we create the box, add the Components to it
    // then add the box itself to BorderL's layout.
    // A box can only use BoxLayout for its layout manager
    Box b = Box.createHorizontalBox();

    // Extra buttons to help demonstrate the box layout. 
    // They will be nonfunctional.
    b.add(new JButton("Dummy"));
    b.add(Box.createHorizontalGlue());
    b.add(new JLabel("Click, then enter text:"));
    b.add(Box.createHorizontalStrut(10));
    b.add(userInput);
    b.add(Box.createHorizontalGlue());
    b.add(new JButton("Dummy"));
    b.add(Box.createHorizontalGlue());

    cPane.add(b, BorderLayout.SOUTH);
  }
}
