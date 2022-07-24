import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DuressJ_new extends Frame{
	
	   public boolean pumpState;
	   private PumpCanvas pumpCanvas;
	   
	   public final static boolean OFF = false;
	   public final static boolean ON = true;
	   public static boolean killswitchstate; 
	   public static boolean pumpDisplay;
//	   public static Log log = new Log();
	  
	   JLabel label1 = null;
		JLabel label2 = null;
		JTextArea htmlTextArea;
		   public JTextArea displayText;

	
	public void secondscreen(Simulator simulator) {
		Frame secondScreen = new Frame();
		secondScreen.setBounds(0, 0, 800, 600);
     secondScreen.setTitle("Intermediate Screen");
     secondScreen.setBackground(Color.white);
     secondScreen.show();

     pump(secondScreen, simulator);
     Intermediate_valve(secondScreen, simulator);
		
	}
	
	public void IntermediateNode() {
		Frame intermediateNode = new Frame();
		intermediateNode.setBounds(0, 0, 800, 600);
		intermediateNode.setTitle("Intermediate Screen");
		intermediateNode.setBackground(Color.white);
		intermediateNode.show();
		//intermediateNode.add(simulator2);
	}
	
	
	public void pump(Frame secondScreen , Simulator simulator) {
		JButton button=new JButton("Click Here to start PA");
        JButton button2=new JButton("Click Here to start/stop PB");
        JButton killswitchbutton=new JButton();
        JButton button3=new JButton("");
 		
//        pumpState = OFF;
		button.setBounds(0,50,250,30);
 		button2.setBounds(0,100,250,30);
 		killswitchbutton.setBounds(0,150,90,30);
 		button3.setBounds(0,200,100,30);
 		button3.setForeground(Color.getColor(getName()));
 		
 		
 		
 		secondScreen.add(button3);
 		
 			
 			
 			
// 			Pump PA 
 			
 		secondScreen.add(button);
 		
 		button.addMouseListener(new MouseAdapter() {  
             public void mouseClicked(MouseEvent event) {
             	boolean res = popupScreen();
             	if(res == true) {
//             		JLabel label1 = new JLabel();
//                 	
//                 	label1.setBounds(0,300,300,200);
//                 	secondScreen.add(label1);
//                 		label1.setText("PumpA status ="+simulator.PA.pumpState+"\n"); 
//                 	if (killswitchstate  == OFF){	
//                 		log.printLog("Hello this is SUNNY");
	 	                if(simulator.PA.pumpState == OFF) {
	 	                	button.setText("Pump ON");
	 	                	simulator.PA.setPumpState(ON);}
	 	                else {
	 	                	button.setText("Pump OFF");
	// 	                	button.setForeground(getForeground());
	 	                	simulator.PA.setPumpState(OFF);}
	 	                //Signe: added logging
	 	                if(Simulator.log_started)
	 	                   Simulator.log.updatePump(simulator.PA.pumpState, getName());
//             }
                 	
             	}
             }});
 		
 		
// 		Pump PB button 
 		secondScreen.add(button2);
 		button2.addMouseListener(new MouseAdapter() {  
      public void mouseClicked(MouseEvent event) {
      	boolean res = popupScreen();
      	if(res == true) {
      		
              if(simulator.PB.pumpState == OFF) {
              	//button.setText("Pump ON");
              	simulator.PB.setPumpState(ON);}
              else {
              	//button.setText("Pump OFF");
//              	button.setForeground(getForeground());
              	simulator.PB.setPumpState(OFF);}
              //Signe: added logging
              if(Simulator.log_started)
                 Simulator.log.updatePump(simulator.PB.pumpState, getName());
      	}
      	}});
 		
 		// Kill switch Button
 		killswitchstate = OFF;
 		pumpDisplay = OFF;
 		killswitchbutton.setText("KILL SWITCH");
 		
 		//	button1.setLayout();
 			secondScreen.add(killswitchbutton);
 			killswitchbutton.addMouseListener(new MouseAdapter() {  
 		      public void mouseClicked(MouseEvent event) {
 		          if(killswitchstate == OFF) {
 		        	 pumpDisplay = ON;
 		        	 killswitchstate = ON;
 		        	System.out.println("I am setting Kill Switch ON :: " + killswitchstate);
 		          	
 		           }
 		          else {
 		        	 killswitchstate = OFF;
 		        	pumpDisplay = OFF;
 		        	System.out.println("I am setting Kill Switch OFF :: " + killswitchstate);
 		          	
 		          }
 		//          //Signe: added logging
 		          
 		      }});
// 			killswitchbutton.addChangeListener(new ChangeListener() {
// 		      public void stateChanged(ChangeEvent event) {
// 		      	if(killswitchstate == OFF) {
// 		          	setKillSwitchState(ON,killswitchbutton);
// 		      	}
// 		          else {
// 		          	setKillSwitchState(OFF,killswitchbutton); }
// 		         }
// 		      });
 			
	}
	
	
//	protected void setKillSwitchState(boolean on2, JButton button1) {
////		// TODO Auto-generated method stub
//    	if (killswitchstate == ON) {
//    		 System.out.println("I am inside setKillSwitchState setting OFF = " + killswitchstate);
//    		button1.setText("KILL SWITCH ON");
//    		//button1 = new JButton("");
//    		killswitchstate = OFF; }
//    	else {
//    		 System.out.println("I am inside setKillSwitchState setting ON = " + killswitchstate);
//    		button1.setText("KILL SWITCH OFF");
//    		killswitchstate = ON; }
//    	
//	}
	public void Intermediate_valve(Frame secondScreen , Simulator simulator) {
		
//		secondScreen.add(Valve.paint);
//		 Slider creation
    
    
    final JSlider valveSliderA;
    valveSliderA = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
//    valveSlider1.setMajorTickSpacing(20);
	valveSliderA.setBounds( 0, 300, 150, 50 );
	valveSliderA.setMajorTickSpacing(10); //Signe: modified tick spacing
      valveSliderA.setMinorTickSpacing(9);
      valveSliderA.setBorder(LineBorder.createBlackLineBorder());
//      valveSliderA.setPaintTicks(true);
//      valveSliderA.setPaintTrack(false);
       secondScreen.add(valveSliderA);
//    		  VA.getValveSliderCanvas());
	
//		secondScreen.add(valveSliderA);
		valveSliderA.addChangeListener(new ChangeListener() {
          public void stateChanged(ChangeEvent event) {
             JSlider source = (JSlider)event.getSource();
             if(source.getValueIsAdjusting()) {
            	 
            	 simulator.VA.setValveSetting((double)(source.getValue() / 10.0));
            	 simulator.VA.setSlider((double)(source.getValue() / 10.0));
             }
             }
          });
		
	}
	
//	public final void setValveSlider(int newValue) {
//	      valveSlider.setValue(newValue);
//	      }
	
	public static boolean popupScreen() {
        JFrame jFrame = new JFrame();
        int result1 = JOptionPane.showConfirmDialog(jFrame, "Do you want to Turn on/off the Pump?");
        boolean result = false;
        if (result1 == 0) {
//            System.out.println("You pressed Yes");
        	
        	result = true;
        }
        else if (result1 == 1) {
//            System.out.println("You pressed NO");
            result = false;
        }
        return result;
    }
	
}