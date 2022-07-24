// TemperatureMeterCanvas.class  This class defines the graphical layout of
//                               temperature meters used throughout the interface
//
// Developed for:  University Of Toronto
//                 Faculty of Applied Science and Engineering
//                 Department of Mechanical and Industrial Engineering
//                 Cognitive Engineering Laboratory
//
//    Written by:  Bruno Cosentino
//
//   Last Update:  March 31, 1999
//
//

import java.awt.*;

public final class TemperatureMeterCanvas extends Canvas {

   private double setting;
   private double maximum;

   public TemperatureMeterCanvas(double initSetting, double max) {
      setting = initSetting;
      maximum = max;
      }

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

      g.drawRect(0, 0, x*5/7, y*14/18);
      // draw meter labels
      g.setFont(new Font("SansSerif", Font.PLAIN, y/11+1));
      g.drawString("" + (int)maximum, x*5/7+1, y*14/18-6-(y-y*4/18-18));
      g.drawString("0", x*5/7+2, y*14/18-6);
      // draw 'ticks' on the meter
      for(int i=0; i<=10; i++)
         if(i%5==0)
            g.drawLine(x*4/7, y*14/18-9-i*(y-y*4/18-18)/10, x*5/7, y*14/18-9-i*(y-y*4/18-18)/10);
         else
            g.drawLine(x*9/14, y*14/18-9-i*(y-y*4/18-18)/10, x*5/7, y*14/18-9-i*(y-y*4/18-18)/10);
      // draw 'pipe' underneath the meter
      g.fillOval(x*3/7-1, y*16/18-1, 3, 3);
      g.drawLine(x*3/7, y*14/18, x*3/7, y*16/18);
      g.drawLine(0, y*16/18, x, y*16/18);
      // draw the yellow massflow indicator bar
      if(setting != 0) {
         g.drawRect(x/7, (int)(y*14/18-9-setting*(y-y*4/18-18)/maximum)+1, x*2/7, (int)(setting*(y-y*4/18-18)/maximum)+1);
         g.setColor(Simulator.COLOR_TEMPERATURE);
         g.fillRect(x/7+1, (int)(y*14/18-9-setting*(y-y*4/18-18)/maximum)+2, x*2/7-1, (int)(setting*(y-y*4/18-18)/maximum));
         }
      }

   public final void setTemperatureSetting(double newSetting) {
      int x = getSize().width;
      int y = getSize().height;

      if((int)(setting*(y-y*4/18-18)/maximum) != (int)(newSetting*(y-y*4/18-18)/maximum)) {
         setting = newSetting;
         repaint();
         }
      }

   }
