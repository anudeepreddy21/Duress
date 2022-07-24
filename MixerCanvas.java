// MixerCanvas.class    This class defines the 'Mixer'
//                      drawing routine for the interface.
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

public final class MixerCanvas extends Canvas {

   public void paint(Graphics g) {
      int x = getSize().width;
      int y =getSize().height;

      g.drawLine(0, y*16/90, x*3/4, y*8/90);
      g.drawLine(0, y*61/90, x*3/4, y*8/90);
      g.drawLine(x*3/4, y*8/90, x, y*8/90);

      g.drawLine(0, y*34/90, x*3/4, y*53/90);
      g.drawLine(0, y*79/90, x*3/4, y*53/90);
      g.drawLine(x*3/4, y*53/90, x, y*53/90);
      }

   }
