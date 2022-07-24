// ValveLabelCanvas.class  This class defines the canvas for valve labels.
//			It was originally in Valve.java.
//
// Developed for:  University Of Toronto
//                 Faculty of Applied Science and Engineering
//                 Department of Mechanical and Industrial Engineering
//                 Cognitive Engineering Laboratory
//
//    Written by:  Bruno Cosentino
//                 &  Alex S. Ross
//
//    Modified by: Signe Bray
//		  Advanced Interface Design Lab
//		  University of Waterloo
//
//    Date: April, 2003
//
//    Modifications:
//	made into own file so that the class could be extended by
//	valvespeciallabelcanvas
//
//


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class ValveLabelCanvas extends Canvas {

   private double maximum;

   public ValveLabelCanvas(double max) {
      maximum = max;
      }

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

      g.setFont(new Font("SansSerif", Font.PLAIN, y/11));
      g.drawString("" + (int)maximum, 1, y*14/18-6-(y-y*4/18-18));
      g.drawString("0", 1, y*14/18-6);
      g.drawLine(0, y*16/18, x, y*16/18);
      }

   }