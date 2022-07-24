// Heater.class   This class defines all UI components,
//                drawing routines and thermodynamic equations
//                for the 'heater' component used throughout the
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
//    Modified by: Signe Bray & Ed Barsalou
//		  Advanced Interface Design Lab
//		  University of Waterloo
//
//    Date: April, 2003
//
//    Modifications:
//	1 - added getName method and writing changes to log file
//	2 - modified scale of sliders for precision
//	
//
//
//

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public final class Heater {            //    note:       heat = energy (W)

   private String name;
   private double maximumSetting;
   private double setting;
   private double opening; 
   private double heatFlowOut;
   private double maximumHeatFlowOut;
   private double timeConstant;
   private int faultTime;
   private double faultSetpoint;
   private HeaterCanvas heaterCanvas;
   private HeaterSliderCanvas heaterSliderCanvas;
   private HeaterMeterCanvas heaterMeterCanvas;
   private HeaterLabelCanvas heaterLabelCanvas;

   public Heater(String name, double maxSet, double initSet, double initHeat,
                 double maxHeat, double tC, int ft, double fsp) {
      this.name = name;
      maximumSetting = maxSet;
      setting = initSet;
      opening = initHeat;
      heatFlowOut = initHeat;
      maximumHeatFlowOut = maxHeat;
      timeConstant = tC;
      faultTime = ft;
      faultSetpoint = fsp;
      heaterCanvas = new HeaterCanvas();
      heaterSliderCanvas = new HeaterSliderCanvas(this, (int)maximumSetting);
      heaterSliderCanvas.setHeaterSlider((int)setting);
      heaterMeterCanvas = new HeaterMeterCanvas(setting, maximumSetting);
      heaterLabelCanvas = new HeaterLabelCanvas(name, (int)maximumSetting);
      }

   public final void setHeatFlowOut(int t, int dt) {
      double localSetting = setting;

      // is heater broken?
      if(faultTime != Simulator.NEVER && faultTime <= t)
         localSetting = setting*faultSetpoint/100;

      // calculate heater opening
      opening = Flow.max(0, Flow.min(opening, maximumHeatFlowOut));

      // add the derivative
      if(opening >= 0 || opening <= maximumHeatFlowOut)
         opening += (localSetting - opening)*dt/timeConstant;

      //calculate heatFlowOut
      heatFlowOut = maximumHeatFlowOut*opening/maximumSetting;
      heaterMeterCanvas.setMeterSetting(opening);

      }

   public final double getHeatFlowOut() {
      return heatFlowOut;
      }

   public final void setSetting(double newSetting) {
      setting = newSetting;
      }

   public final HeaterSliderCanvas getHeaterSliderCanvas() {
      return heaterSliderCanvas;
      }

   public final HeaterCanvas getHeaterCanvas() {
      return heaterCanvas;
      }

   public final HeaterMeterCanvas getHeaterMeterCanvas() {
      return heaterMeterCanvas;
      }

   public final HeaterLabelCanvas getHeaterLabelCanvas() {
      return heaterLabelCanvas;
      }

   //Signe: added methog for log.java to know which heater instance was modified
   public final String getName () {
       return name;
   }

   }


final class HeaterCanvas extends Canvas {

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

//      g.drawRect(0, 0, x-1, y+1);
      g.setColor(Color.red);
      g.drawLine(x*2/20, y*3/4, x*4/20, y*3/4);
      g.drawLine(x*4/20, y*3/4, x*6/20, y*1/4);
      g.drawLine(x*6/20, y*1/4, x*8/20, y*3/4);
      g.drawLine(x*8/20, y*3/4, x*10/20, y*1/4);
      g.drawLine(x*10/20, y*1/4, x*12/20, y*3/4);
      g.drawLine(x*12/20, y*3/4, x*14/20, y*1/4);
      g.drawLine(x*14/20, y*1/4, x*16/20, y*3/4);
      g.drawLine(x*16/20, y*3/4, x*18/20, y*3/4);
      }

   }


final class HeaterSliderCanvas extends JPanel {

   private JSlider heaterSlider;
   private Heater heater;

   public HeaterSliderCanvas(Heater h, int maxSet) {  
      heater = h;
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



// Ed: Modified the max value so we can scale it to a single decimal point - otherwise the heat
//     wouldn't balance.  With the mult by 10, we later div by 10 to get a single decimal point


//	Old line:
//      heaterSlider = new JSlider(JSlider.HORIZONTAL, 0, maxSet, 0);

//    New line:
      heaterSlider = new JSlider(JSlider.HORIZONTAL, 0, maxSet * 10, 0);


      heaterSlider.addChangeListener(new ChangeListener() {
                                        public void stateChanged(ChangeEvent event) {
                                        	if(!DuressJ_new.killswitchstate) {
                                           JSlider source = (JSlider)event.getSource();

					   // Ed: source.getValue() is now divided by 10.0 to scale
					   //     to a one-digit decimal (must be .0 - or else it 
					   //     won't cast to double)

                                           if(!source.getValueIsAdjusting())
                                              heater.setSetting((double)(source.getValue() / 10.0)); //ed: /10.0
                                           

					   // Ed: source.getValue() is now divided by 10.0 to scale
					   //     to a one-digit decimal (must be .0 - or else it 
					   //     won't cast to double)
                                          //Signe
                                          if(Simulator.log_started)
                                           Simulator.log.updateHeater((double)(source.getValue() / 10.0), heater.getName()); //ed: /10.0
                                           //Simulator.logTime = true;
                                           }
                                        }
                                        });
      heaterSlider.setMajorTickSpacing(10*maxSet/2); //Signe: modified tick spacing..
      heaterSlider.setMinorTickSpacing(10*maxSet/10);
      heaterSlider.setPaintTrack(false);
      heaterSlider.setBorder(LineBorder.createBlackLineBorder());
      add(heaterSlider);
      }

   public final JSlider getHeaterSlider() {
      return heaterSlider;
      }

   public final void setHeaterSlider(int newValue) {
      heaterSlider.setValue(newValue);
      }

   }


final class HeaterMeterCanvas extends Canvas {

   private double setting;
   private double maximum;

   public HeaterMeterCanvas(double initSetting, double max) {
      setting = initSetting;
      maximum = max;
      }

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

      g.drawRect(0, 0, x-1, y-1);
      // draw 'ticks' on meter
      for(int i=0; i<=10; i++)
         if(i%5==0)
            g.drawLine(10+i*(x-20)/10, y*4/6, 10+i*(x-20)/10, y);
         else
            g.drawLine(10+i*(x-20)/10, y*5/6, 10+i*(x-20)/10, y);
      // draw heat-energy output indicator
      if(setting != 0) {
         g.drawRect(10, y/6, (int)(setting*(x-20)/maximum)+1, y*2/6+1);
         g.setColor(Color.red);
         g.fillRect(11, y/6+1, (int)(setting*(x-20)/maximum), y*2/6);
         }
      }

   public final void setMeterSetting(double newSetting) {
      int x = getSize().width;
      int y = getSize().height;

      if((int)(setting*(x-20)/maximum) != (int)(newSetting*(x-20)/maximum)) {
         setting = newSetting;
         repaint();
         }
      }

   }


final class HeaterLabelCanvas extends Canvas {

   private String name;
   private int maximum;

   public HeaterLabelCanvas(String name, int max) {
      this.name = name;
      maximum = max;
      }

   public void paint(Graphics g) {
      int x = getSize().width;
      int y = getSize().height;

      g.setFont(new Font("SansSerif", Font.PLAIN, y));
      g.drawString("0", 3, y);
      g.drawString(name, x/2-6, y);
      g.drawString("" + maximum, x-14, y);
      }

   }
