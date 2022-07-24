// Pump.class     This class defines all UI components,
//                drawing routines and thermodynamic equations
//                for the two 'pump' components used in the
//                DuressJ interface.
//
// Developed for:  University Of Toronto
//                 Faculty of Applied Science and Engineering
//                 Department of Mechanical and Industrial Engineering
//                 Cognitive Engineering Laboratory
//
//    Written by:  Bruno Cosentino
//                 &  Alex S. Ross
//
//   Last Update:  March 31, 1999
//
//    Modified by: Signe Bray
//		  Advanced Interface Design Lab
//		  University of Waterloo
//
//    Date: April, 2003
//
//    Modifications:
//	1 - added writing to log file 
//
//
//

import java.awt.*;
import java.awt.event.*;

public final class Pump extends Flow {
    
   public final static boolean OFF = false;
   public final static boolean ON = true;
   boolean pumpState;  
   // either ON=1 or OFF=0  **Signe: modified to boolean
   private double minimumMassFlow;     // minimum mass flow allowed while pump is ON
   private double maximumPipeFlow;    // maximum allowable flow upstream from pump
   private double timeConstant;
   private int faultTime;            // time at which pump breaks
   private int breakTime;           // time to blow-up if flow is constricted
   private int timeLeft;           // (countdown timer) time counter for blow-up
   private PumpCanvas pumpCanvas;
   
  // private KillSwitchPumpCanvas killSwitchPumpCanvas;
//   public DuressJ_new duressj_new = new DuressJ_new();

   public Pump(String name, boolean initState, double min, double max,
               double tC, int faultT, int breakT) {
      super(name, max);
      pumpState = initState;
      minimumMassFlow = min;
      timeConstant = tC;
      faultTime = faultT;
      breakTime = breakT;
      timeLeft = Simulator.NEVER;
      pumpCanvas = new PumpCanvas(getName(), pumpState);
      //killSwitchPumpCanvas = new  KillSwitchPumpCanvas(getName(), pumpState);
      pumpCanvas.addMouseListener(new MouseAdapter() {
                                     public void mouseClicked(MouseEvent event) {
                                    	 boolean res = DuressJ_new.popupScreen();
                                    	 if(res == true) {
//                                    		 System.out.println("I am inside Main PA button action and killSwitch state  is = " + DuressJ_new.killswitchstate);
//                                    		 System.out.println("I am inside Main PA button action and pumpstate is = " + pumpState);
                                    	 
                                    		 
                                         if(pumpState == OFF)
                                             setPumpState(ON);
                                         else
                                             setPumpState(OFF);

                                         //Signe: added logging
                                         if(Simulator.log_started)
                                            Simulator.log.updatePump(pumpState, getName());
                                    	 }
                                    	
                                    	 
                                     }});
      }

   public final void setPumpState(boolean newState) {
//	   System.out.println("I am inside setPumpState and killswitch is :: " + DuressJ_new.killswitchstate);
	   if (!DuressJ_new.killswitchstate) {
		   pumpState = newState;
		   }
	   else {
		   System.out.println("Im inside the killer swiiitch"+newState);
		   if(!newState) {
			   System.out.println("Im inside the killer-------------------OFF-------------- swiiitch"+newState);
			   pumpState = (ON);
		   }
		   if (newState) {
			   System.out.println("Im inside the killer-------------------ON-------------- swiiitch"+newState);
			   pumpState = OFF;
			   
		   }		   
	   }
      pumpCanvas.setPumpState(pumpState);

      
      }

   public final void setMaximumPipeFlow(double valveOpen, double splitterFlow) {
      maximumPipeFlow = min(valveOpen, splitterFlow);
      }

   public final void setMassFlowOut(int t, int dt) {
      boolean localState = pumpState;
      double maxFlow, currentFlow;

      // check to see if pump is broken
      if(faultTime != Simulator.NEVER && faultTime <= t) {
        // setMaximumMassFlowOut(0);  Signe: trying to fix...
         localState = OFF;
         }

      // calculate maximum allowable massFlowOut
      if(localState == ON)
         maxFlow = min(getMaximumMassFlowOut(), maximumPipeFlow);
      else
         maxFlow = 0;  // pump is broken of OFF

      // calculate massFlowOut
         currentFlow = max(0, min(getMassFlowOut(), maxFlow));
      // add the derivative
      if(getMassFlowOut() >= 0 || getMassFlowOut() <= getMaximumMassFlowOut())
         currentFlow += (maxFlow-getMassFlowOut())*dt/timeConstant;
      super.setMassFlowOut(currentFlow);

      // check to see if pump should blow-up
      if(localState == ON && getMassFlowOut() < minimumMassFlow)
         if(timeLeft == Simulator.NEVER)
            timeLeft = breakTime;
         else if(timeLeft <= 0) {
            //  **gen. error****  (note: stop the thread)
            setMaximumMassFlowOut(0);
            }
         else
            timeLeft -= dt;
      else
         timeLeft = Simulator.NEVER;
      
      }

   public final PumpCanvas getPumpCanvas() {
      return pumpCanvas;
      }

   }



final class PumpCanvas extends Canvas {

   private String name;
   private boolean pumpState;
   private boolean pumpDisplay;

   public PumpCanvas(String name, boolean initState) {
      this.name = name;
      pumpState = initState;
      pumpDisplay = DuressJ_new.pumpDisplay;
      }

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;
//      System.out.println("I am inside Main paint and pumpState is = " + pumpState);
      g.setFont(new Font("SansSerif", Font.PLAIN, y/11));
      g.drawString(name, x/7, y*14/18);
      g.drawLine(0, y*16/18, x, y*16/18);
//      System.out.println("I am inside Main paint and killswitchstate is = " + DuressJ_new.killswitchstate);
      if (DuressJ_new.killswitchstate) {
    	  if(pumpDisplay) {
		         g.fillOval(0, y*15/18, x*4/7, y*3/18);
		         g.fillRect(x*2/7, y*15/18, x*3/7, y*3/36);
		         g.setColor(Color.white);
		         g.drawString("ON", x/14, y*17/18);
		         pumpDisplay = false;
		         }
		      else {
		         g.setColor(Color.gray);
		         g.fillOval(0, y*15/18, x*4/7, y*3/18);
		         g.fillRect(x*2/7, y*15/18, x*3/7, y*3/36);
		         g.setColor(Color.black);
		         g.drawString("OFF", x/14+2, y*17/18);
		         pumpDisplay = true;
		         }
      } 
      else {
      if(pumpState == Pump.OFF) {
         g.fillOval(0, y*15/18, x*4/7, y*3/18);
         g.fillRect(x*2/7, y*15/18, x*3/7, y*3/36);
         g.setColor(Color.white);
         g.drawString("OFF", x/14, y*17/18);
         }
      else {
         g.setColor(Color.gray);
         g.fillOval(0, y*15/18, x*4/7, y*3/18);
         g.fillRect(x*2/7, y*15/18, x*3/7, y*3/36);
         g.setColor(Color.black);
         g.drawString("ON", x/14+2, y*17/18);
         }
      }
      }
   

   public final void setPumpState(boolean newState) {
//	   System.out.println("I am inside second setPumpState and killswitch is :: " + DuressJ_new.killswitchstate);
      if(newState != pumpState){
    	  if (!DuressJ_new.killswitchstate) {
   		   pumpState = newState;
   	   }
         
         }
//      System.out.println("I am inside setPumpState repaint");
      repaint();
      }

   }
