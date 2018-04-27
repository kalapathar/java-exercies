import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***********************************************************************
   GridBagL is a subclass of the Scrib Applet that uses GridBagLayout,
   whereas Applets use FlowLayout by default.  

   Ordinarily, we would expect to choose the layout manager in Scrib.
   However, Scrib has no components of its own that need layout management,
   so we will do it here in the subclass that adds such components.
************************************************************************/

public class GridBagL extends Scrib {
  JPanel controls;
  JButton clearButton;
  JTextField userInput;  // for input of text
  int textX, textY;    // position to draw next text
  JLabel xPosition, yPosition;  // to show current mouse position

  public void init() {
    super.init();
    this.addMouseListener(new MouseClickAdapter());
    this.addMouseMotionListener(new MouseMovedAdapter());

    controls = new JPanel();
    //controls.setSize(200,200);
    cPane.add(controls);

    // initialize the layout manager and a GridBagConstraints object c
    controls.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;  // components grow in both dimensions
    c.insets = new Insets(5,5,5,5);    // 5-pixel margins on all sides

    clearButton = new JButton("Clear");
    clearButton.addActionListener(new ClearButtonAdapter());
    // the following line describes intended grid position for this button
    c.gridx = 0; c.gridy = 0; c.gridwidth = 1; c.gridheight=4;
    controls.add(clearButton, c);

    c.gridx = 1; c.gridy = 0; c.gridwidth = 4; c.gridheight=1;
    controls.add(new JLabel("Text entry:"), c);

    textX = textY = 50;  // text draw position before first click
    userInput = new JTextField(11);
    userInput.addActionListener(new InputAdapter());
    c.gridx = 1; c.gridy = 1; c.gridwidth = 4; c.gridheight=1;
    controls.add(userInput, c);  // add TextField to this applet's Image

    c.gridx = 2; c.gridy = 2; c.gridwidth = 4; c.gridheight=1;
    controls.add(new JLabel("Position:"), c);

    c.gridx = 1; c.gridy = 3; c.gridwidth = 1; c.gridheight=1;
    controls.add(new JLabel("x ="), c);

    xPosition = new JLabel("?");
    c.gridx = 2; c.gridy = 3; c.gridwidth = 1; c.gridheight=1;
    controls.add(xPosition, c);

    c.gridx = 3; c.gridy = 3; c.gridwidth = 1; c.gridheight=1;
    controls.add(new JLabel(", y ="), c);

    yPosition = new JLabel("?");
    c.gridx = 4; c.gridy = 3; c.gridwidth = 1; c.gridheight=2;
    controls.add(yPosition, c);
  }

  class MouseClickAdapter extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      textX = e.getX();
      textY = e.getY();
    }
  }
  
  
  class MouseMovedAdapter extends MouseMotionAdapter {
    public void mouseMoved(MouseEvent e) {
      xPosition.setText(String.valueOf(e.getX()));
      yPosition.setText(String.valueOf(e.getY()));
    }
  }
  
  
  class ClearButtonAdapter implements ActionListener {
    // Invoked when the  user presses the clearButton 
    public void actionPerformed(ActionEvent e) { // clear the scribble
      /*
      Graphics g = getGraphics();  // get a Graphics for Scrib
      Color c = g.getColor();
      g.setColor(getBackground());
      g.fillRect(0, 0, getSize().width, getSize().height);
      g.setColor(c);
      */
      cPane.repaint();
    }
  }


  class InputAdapter implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Graphics g = getGraphics();  // get a Graphics for Scrib
      g.setColor(getForeground());
      g.drawString(userInput.getText(), textX, textY);
      userInput.setText("");
    }
  }
}
