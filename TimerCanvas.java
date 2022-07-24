// TimerCanvas.class    This class defines the graphical layout,
//                      and counter for the timer mechanism.
//
// Developed for:  University Of Toronto
//                 Faculty of Applied Science and Engineering
//                 Department of Mechanical and Industrial Engineering
//                 Cognitive Engineering Laboratory
//
//    Written by:  Bruno Cosentino
//
//   Last Update:  March 28, 1999
//
//

import java.awt.*;

public final class TimerCanvas extends Canvas {

   private int hour;
   private int minute;
   private int second;

   public TimerCanvas() {
      hour = 0;
      minute = 0;
      second = 0;
      }

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

      g.setFont(new Font("SansSerif", Font.PLAIN, x*3/10));
      if(minute < 10 && second < 10)
         g.drawString(hour + ":" + "0" + minute + ":" + "0" + second, 0, y);
      else if(minute < 10)
         g.drawString(hour + ":" + "0" + minute + ":" + second, 0, y);
      else if(second < 10)
         g.drawString(hour + ":" + minute + ":" + "0" + second, 0, y);
      else
         g.drawString(hour + ":" + minute + ":" + second, 0, y);
      }

   public final void setTime(int threadTime) {
      hour = (threadTime/3600)%60;
      minute = (threadTime/60)%60;
      second = threadTime%60;
      repaint();
      }

   }
