import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Background extends Scribble{
	JButton clearButton,backgroundButton;
	public void init(){
	super.init();
	this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
	clearButton = new JButton("Clear");
    
    clearButton.addActionListener(new MyclearAdapter());
    clearButton.setForeground(Color.black);
    clearButton.setBackground(Color.lightGray);
    backgroundButton=new JButton("Background");
    backgroundButton.addActionListener(new MybackgroundAdapter());
    backgroundButton.setForeground(Color.black);
    backgroundButton.setBackground(Color.lightGray);
    cPane.add(backgroundButton);
    
    cPane.add(clearButton);
	}

	class MyclearAdapter implements ActionListener{
    public void actionPerformed(ActionEvent e) { // clear the scribble
      // Repaint to display the buttons, uses the already set background color
      cPane.repaint();
      //cPane.setBackground(colorVec[colorIndex]);
  }
}

class MybackgroundAdapter implements ActionListener{
    public void actionPerformed(ActionEvent e) { // clear the scribble
      // Repaint to display the buttons, uses the already set background color
      //cPane.repaint();
      cPane.setBackground(colorVec[colorIndex]);
  }
}
}