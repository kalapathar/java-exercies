import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***********************************************************************
   BorderL is a subclass of the Scrib Applet that uses BorderLayout,
   whereas Applets use FlowLayout by default.  

   Ordinarily, we would expect to choose the layout manager in Scrib.
   However, Scrib has no components of its own that need layout management,
   so we will do it here in the subclass that adds such components.
************************************************************************/

public class BorderL extends Scrib {
  JButton clearButton;
  JTextField userInput;  // for input of text
  int textX, textY;    // position to draw next text

  public void init() {
    cPane = this.getContentPane();
    super.init();
    this.addMouseListener(new MouseClickAdapter());

    // Note:  BorderLayout is the default for a ContentPane
    cPane.setLayout(new BorderLayout());  

    clearButton = new JButton("Clear");
    clearButton.addActionListener(new ClearButtonAdapter());
    clearButton.setForeground(Color.black);
    clearButton.setBackground(Color.lightGray);
    cPane.add(clearButton, BorderLayout.NORTH);
    
    textX = textY = 50;  // text draw position before first click
    userInput = new JTextField(11);
    userInput.addActionListener(new InputAdapter());
    cPane.add(userInput, BorderLayout.SOUTH);  
    // add TextField to this applet's Image

    // the following call would put the label ON TOP OF userInput... 
    // cPane.add(new JLabel("Text input:"), BorderLayout.SOUTH);    
  }


  class MouseClickAdapter extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      textX = e.getX();
      textY = e.getY();
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
