// TemperatureNameCanvas.class   This class defines all is used to
//                               embed a string on top of temperautre meters
//                               found throughout DuressJ
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

public final class TemperatureNameCanvas extends Canvas {

   private String name;

   public TemperatureNameCanvas(String name) {
      this.name = name;
      }

   public void paint(Graphics g) {
      int y = getSize().height;

      g.setFont(new Font("SansSerif", Font.PLAIN, y));
      g.drawString(name, 0, y-2);
      }

   }
