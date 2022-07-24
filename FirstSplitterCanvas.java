// FirstSplitterCanvas.class  This class defines the graphical layout of
//                            the pipes before the pump stage.
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

final class FirstSplitterCanvas extends Canvas {

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

      g.drawLine(0, y*52/90, x, y*25/90);
      g.drawLine(0, y*52/90, x, y*70/90);
      }

   }
