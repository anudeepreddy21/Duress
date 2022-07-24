//  ImageCanvas.java v1.0
//
//  Written by:   Bruno Cosentino
//                (Adapted from David M.Geary,
//                 "Graphic Java 1.1: Mastering the AWT" 2nd Ed.)
//
//

import java.awt.*;

class ImageCanvas extends Canvas {

   private Image image;

   public ImageCanvas(Image image) {
      MediaTracker mt = new MediaTracker(this);
      mt.addImage(image, 0);
      try {
         mt.waitForID(0);
         }
      catch(Exception e) {
         e.printStackTrace();
         }
      this.image = image;
      }

   public void paint(Graphics g) {
      g.drawImage(image, 0, 0, this);
      }

   public void update(Graphics g) {
      paint(g);
      }

   public Dimension getPreferredSize() {
      return new Dimension(image.getWidth(this), image.getHeight(this));
      }

   }
