// Splitter.class  This class defines the 'Splitter'
//                component found in the system.  It
//                contains the data fields
//                (variables) and drawing routines.
//
// Developed for:  University Of Toronto
//                 Faculty of Applied Science and Engineering
//                 Department of Mechanical and Industrial Engineering
//                 Cognitive Engineering Laboratory
//
//    Written by:  Bruno Cosentino
//                 &  Alex S. Ross
//
//   Last Update:  March 28, 1999
//
//

import java.awt.*;

public final class Splitter extends Flow {

   private double massFlowOut2;
   private double temperatureOut2;
   private double maximumAllowableMassFlow;
   private SplitterCanvas splitterCanvas;

   public Splitter(String name, double max) {
      super(name, max);
      splitterCanvas = new SplitterCanvas();
      }

   public final void calculateResistance(double valveOpen1, double valveOpen2) {
      // calculate maximum allowed to go through valves and spliiter
      maximumAllowableMassFlow = min(getMaximumMassFlowOut(), valveOpen1 + valveOpen2);
      }

   public final double getMaximumAllowableMassFlow() {
      return maximumAllowableMassFlow;
      }

   public final void setMassFlowOut(double massIn, double valveOpen1, double valveOpen2) {
      if(valveOpen1 + valveOpen2 > 0) {
         // update mass flow through first port
         super.setMassFlowOut(massIn*valveOpen1/(valveOpen1+valveOpen2));
         // update mass flow through second port
         massFlowOut2 = massIn - getMassFlowOut();
         }
      else {
         super.setMassFlowOut(0);
         massFlowOut2 = 0;
         }
      }

   public final double getMassFlowOut2() {
      return massFlowOut2;
      }

   public final void setTemperatureOut(double tempIn) {
      super.setTemperatureOut(tempIn);
      temperatureOut2 = tempIn;
      }

   public final double getTemperatureOut2() {
      return temperatureOut2;
      }

   public final SplitterCanvas getSplitterCanvas() {
      return splitterCanvas;
      }

   }


final class SplitterCanvas extends Canvas {

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

      g.drawLine(0, y*25/45, x, y*16/45);
      g.drawLine(0, y*25/45, x, y*34/45);
      }

   }
