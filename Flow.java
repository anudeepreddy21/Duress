// Flow.class     This is the base class of all thermodynamic
//                components found in the system.  It
//                contains the basic outflow
//                data fields (variables).
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

public class Flow {

   private String name;
   private double massFlowOut;
   private double maximumMassFlowOut;
   private double temperatureOut;
   private FlowCanvas flowCanvas;
   private NameCanvas nameCanvas;

   public Flow(String name) {
      this.name = name;
      flowCanvas = new FlowCanvas();
      nameCanvas = new NameCanvas(name);
      }

   public Flow(String name, double max) {
      this.name = name;
      maximumMassFlowOut = max;
      flowCanvas = new FlowCanvas();
      nameCanvas = new NameCanvas(name);
      }

   public final String getName() {
      return name;
      }

   public void setMassFlowOut(double massOut) {
      massFlowOut = massOut;
      }

   public final double getMassFlowOut() {
      return massFlowOut;
      }

   public void setMaximumMassFlowOut(double maxOut) {
      maximumMassFlowOut = maxOut;
      }

   public final double getMaximumMassFlowOut() {
      return maximumMassFlowOut;
      }

   public void setTemperatureOut(double tempOut) {
      if(getMassFlowOut() > 0)
         temperatureOut = tempOut;
      else
         temperatureOut = 0;
      }

   public final double getTemperatureOut() {
      return temperatureOut;
      }

   public final static double min(double x, double y) {
      return (x<y ? x : y);
      }

   public final static double max(double x, double y) {
      return (x>y ? x : y);
      }

   public final FlowCanvas getFlowCanvas() {
      return flowCanvas;
      }

   public final NameCanvas getNameCanvas() {
      return nameCanvas;
      }

   }


final class FlowCanvas extends Canvas {

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;
      g.drawLine(0, y*16/18, x, y*16/18);
      }

   }


final class NameCanvas extends Canvas {

   private String nameString;

   public NameCanvas(String name) {
      nameString = name;
      }

   public void paint(Graphics g) {
      int y = getSize().height;

      g.setFont(new Font("SansSerif", Font.PLAIN, y));
      g.drawString(nameString, 0, y-2);
      }

   }
