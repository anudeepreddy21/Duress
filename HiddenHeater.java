// HiddenHeater.class   This class defines the thermodynamic behaviour
//                      for the 'hidden-heater' component used throughout the
//                      DuressJ interface.
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

public final class HiddenHeater {

   private String name;
   private double maximumHeatFlowOut;
   private double setting;
   private double opening;
   private double timeConstant;
   private double fault1Setpoint;
   private int fault1Time;
   private double fault2Setpoint;
   private int fault2Time;

   public HiddenHeater(String name, double max, double initSet, double initOpen, double tC,
                       double f1sp, int f1t, double f2sp, int f2t) {
      this.name = name;
      maximumHeatFlowOut = max;
      setting = initSet;
      opening = initOpen;
      timeConstant = tC;
      fault1Setpoint = f1sp;
      fault1Time = f1t;
      fault2Setpoint = f2sp;
      fault2Time = f2t;
      }

   public final void checkForFault(int t, int dt) {
      // check to see if valve is in one of the fault modes
      if(fault2Time != Simulator.NEVER && fault2Time <= t)
         setting = fault2Setpoint;
      else if(fault1Time != Simulator.NEVER && fault1Time <= t)
         setting = fault1Setpoint;

      // calculate opening
      opening = Flow.max(0, Flow.min(opening, maximumHeatFlowOut));
      // add the derivative
      opening += (setting-opening)*dt/timeConstant;
      }

   public final double getHeatFlowOut() {
      return opening;
      }

   public final double getMaximumHeatFlowOut() {
      return maximumHeatFlowOut;
      }

   }
