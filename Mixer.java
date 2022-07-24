// Mixer.class    This class defines the 'Mixer'
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
//   Last Update:  March 2, 1999
//
//

public final class Mixer extends Flow {

   private double massFlowInA;
   private double massFlowInB;

   public Mixer(String name) {
      super(name);
      massFlowInA = 0;
      massFlowInB = 0;
      }

   public final void setMassFlowOut(double massInA, double massInB) {
      massFlowInA = massInA;
      massFlowInB = massInB;
      super.setMassFlowOut(massFlowInA + massFlowInB);
      }

   public final void setTemperatureOut(double tempInA, double tempInB) {
      if(getMassFlowOut() > 0)
         super.setTemperatureOut((massFlowInA*tempInA+massFlowInB*tempInB)/getMassFlowOut());
      else
         super.setTemperatureOut(0);
      }

   }
