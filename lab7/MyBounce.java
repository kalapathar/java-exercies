import java.applet.*;
import java.awt.*;
import javax.swing.*;

/* Demo of threads:  an Applet that creates a bouncing circle, using a
   timer thread to update the animation
   RAB 1/99, inspired by Flanagan, "Java Examples in a Nutshell", 1997

   Main idea:  We create an Applet Bounce whose paint method draws a red
   circle at (x,y) and that has a method doStep() for moving the circle
   by (dx,dy), except reversing direction when the circle reaches a Panel
   boundary.  We also create a separate class TimerThread that calls
   Bounce.doStep() every N milliseconds.
*/

public class MyBounce extends JApplet implements SteppingProcess {
  //int x = 150, y = 50, r = 50;  // position and radius of circle
  //int dx = 11, dy = 7;  // physical direction vector components
Ball ball1;
Ball ball2;
TimerThread timer;

public void init(){
  timer = new TimerThread(this, 100);
  ball1=new Ball(170,40,50,11,7,Color.red);
  ball2=new Ball(200,90,40,15,5,Color.green);
}

  public void paint(Graphics g) {
    Image image = createImage(getSize().width, getSize().height);
    // g.setColor(getBackground());
    // g.fillRect(0, 0, getSize().width, getSize().height);
    // g.setColor(Color.red);
    // g.fillOval(x-r, y-r, r*2, r*2);


    Graphics imageG = image.getGraphics();
    imageG.setColor(getBackground());
    imageG.fillRect(0, 0, getSize().width, getSize().height);
    ball1.draw(imageG);
    ball2.draw(imageG);
    
    g.drawImage(image, 0, 0, this);
  }

  public void doStep() {
    // Bounce if we've hit an edge
    // if ((x - r + dx < 0) || (x + r + dx > getSize().width)) 
    //   dx = -dx;
    // if ((y - r + dy < 0) || (y + r + dy > getSize().height)) 
    //   dy = -dy;
    // x += dx;  y += dy;
    ball1.move();
    ball2.move();
    repaint();
  }

  public void start() { timer.startStepping(); }  // override JApplet.start()

  public void stop() { timer.suspendStepping(); } // override JApplet.stop()



/* SteppingProcess allows Bounce to interact with TimerThread */


public class Ball{
  Color color;
  int r,x,y,dx,dy;
  Ball (int xcor,int ycor, int radius, int dxcor,int dycor,Color c){
    x=xcor;y=ycor;r=radius;dx=dxcor;dy=dycor;color=c;

  }
  public void draw(Graphics g){
    g.setColor(color);
    g.fillOval(x-r,y-r,r*2,r*2);
  }

  public void move(){
   // Bounce if we've hit an edge
   if ((x - r + dx < 0) || (x + r + dx > getWidth()))
    dx = -dx;
    if ((y - r + dy < 0) || (y + r + dy > getHeight()))
    dy = -dy;
    x += dx;  y += dy;
  }
}
}

interface SteppingProcess {
  public void doStep();
}


/* TimerThread is a separate thread of execution that does nothing but 
   call proc.doStep() periodically */

class TimerThread extends Thread {
  SteppingProcess proc;  // process being timed
  int delay;  // wait in milliseconds between steps
  private boolean amSuspended = true;
    // enables/disables the periodic calling of proc.doStep()

  public TimerThread(SteppingProcess p, int d) {
    proc = p;  delay = d;
  }

  public synchronized void startStepping() {
    amSuspended = false;
    if (!isAlive())
      // assert:  run() has not yet been started on this thread
      start();
    else
      notify();
  }

  public synchronized void suspendStepping() {
    amSuspended = true;
  }

  public void run() {
    for (;;) {
      proc.doStep();
      try {
	Thread.sleep(delay);

	synchronized (this) {
	  while (amSuspended)
	    wait();
	}
      } catch (InterruptedException e) {}
    }
  }
}

