//  DuressJ v1.0:  Java version of the DuressII ThermoDynamic Simulator
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
//          Note:  'makeMenu' adapted from
//                 Cay S. Horstmann & Gary Cornell, Core Java
//                 Published By Sun Microsystems Press/Prentice-Hall
//                 Copyright (C) 1997 Sun Microsystems Inc.

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class DuressJ extends Frame implements ActionListener {
	
	public final static boolean OFF = false;
	public final static boolean ON = true;
	

    private MenuBar mainMenu = new MenuBar();
    private Simulator simulator;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    //Signe: static log member, so that it can be updated by other classes.
    public static Log log;

    public static void main(String[] args) {
        Frame mainScreen = new DuressJ();
        
        mainScreen.setBounds(0, 0, 800, 600);
        mainScreen.setTitle("DuressJ");
        mainScreen.show();
        mainScreen.toFront();
        
    }
    public DuressJ() {
    	
        mainMenu.add(makeMenu("File", new Object[] {"Load Scenario...", null, "Exit"}, this));
        mainMenu.add(makeMenu("Simulator", new Object[] {"Start", null, "Pause", "Resume"}, this));
        mainMenu.add(makeMenu("Interface", new Object[] {"Physical", null, "Physical + Functional", null,
            makeMenu("Multi-Level", new Object[] {"Settings Level", "Flows Level", "Principles Level", "Goals Level"}, this)},
                              this));
        //mainMenu.add(makeMenu("Experiment", new Object[]{"Trial Info"}, this));
        setMenuBar(mainMenu);
        //      splash("DuressJ.gif");
        simulator = new Simulator();
        add(simulator);
        
        DuressJ_new duressj_new = new DuressJ_new();
//        
        duressj_new.secondscreen(simulator);
       
       // duressj_new.IntermediateScreen(simulator);
        
//        pump();

//     // Second Screen Start 
////        temp.pump();
//        Frame secondScreen = new Frame();
//         	secondScreen.setBounds(0, 0, 800, 600);
//             secondScreen.setTitle("Intermediate Screen");
//             secondScreen.setBackground(Color.white);
//             secondScreen.show();
//             JButton button=new JButton("Click Here to start PA");
//             JButton button2=new JButton("Click Here to start/stop PB");
//     		button.setBounds(0,50,250,30);
////     		button2.setBounds(0,200,250,30);
//     		
//     		secondScreen.add(button);
//     		
//     	
//     		
////     		button.addActionListener(e-> {
////   			if(simulator.PA.pumpState == OFF) button.setText("Click Here to stop PA");
////   			else if(simulator.PA.pumpState == ON) button.setText("Click Here to start PA");	
////   		});
//     		
//     		  button.addActionListener(new ActionListener() {
//     		        @Override
//     		        public void actionPerformed(ActionEvent event) {
////     		            System.out.println("performing----");
//     		           button.setText("Click Here to stop PA");
//     		          
////     		          button2.setBounds(0,100,250,30);
////     		          button2.setHorizontalAlignment(SwingConstants.LEFT);
//     		        }       
//     		    });
//     		
//     		button.addMouseListener(new MouseAdapter() {  
//                 public void mouseClicked(MouseEvent event) {
//                 	boolean res = popupScreen();
//                 	if(res == true) {
//                 		JLabel label1 = new JLabel();
//                     	
//                     	label1.setBounds(0,300,300,200);
//                     	secondScreen.add(label1);
//                     		label1.setText("PumpA status ="+simulator.PA.pumpState+"\n"); 
//                     		
//     	                if(simulator.PA.pumpState == OFF) {
//     	                	//button.setText("Pump ON");
//     	                	simulator.PA.setPumpState(ON);}
//     	                else {
//     	                	//button.setText("Pump OFF");
////     	                	button.setForeground(getForeground());
//     	                	simulator.PA.setPumpState(OFF);}
//     	                //Signe: added logging
//     	                if(Simulator.log_started)
//     	                   Simulator.log.updatePump(simulator.PA.pumpState, getName());
//                 	}
//                 	
//              
////                 	label1 = new JLabel();
////                 	
////                 	label1.setBounds(0,300,300,200);
////                 	secondScreen.add(label1);
////                 		label1.setText("PumpA status ="+simulator.PA.pumpState+"\n");   
//                 	
////                 	-------- My start --------
////                 		label2 = new JLabel();
////                 		label2.setBounds(0,100,300,200);
////                 		label2.setText("Set pump Off");
////                 	
////                 		
////                 		String initialText = "";
////                  
////                         htmlTextArea = new JTextArea(0, 300);
////                         htmlTextArea.setText(initialText);
////                         htmlTextArea.setBounds(0,300,400,200);
////                         
////                         secondScreen.add(htmlTextArea);
////                 	------------- End ---------
////                 	label1.append("PumpA status ="+simulator.PA.pumpState+"\n");
////                 	label1.setText("Water Flow= "+Flow.massFlowOut);
//                 }});
//     	
//     		
//     		button2.setBounds(0,100,250,30);
//     		secondScreen.add(button2);
//     		JButton button3=new JButton("c");
//     		button3.setBounds(0,150,1,1);
//     		button3.setForeground(Color.WHITE);
//     		
//     		secondScreen.add(button3);
//     		
//     		
//     		button2.addMouseListener(new MouseAdapter() {  
//                 public void mouseClicked(MouseEvent event) {
//                 	boolean res = popupScreen();
//                 	if(res == true) {
//     	                if(simulator.PB.pumpState == OFF) {
//     	                	//button.setText("Pump ON");
//     	                	simulator.PB.setPumpState(ON);}
//     	                else {
//     	                	//button.setText("Pump OFF");
////     	                	button.setForeground(getForeground());
//     	                	simulator.PB.setPumpState(OFF);}
//     	                //Signe: added logging
//     	                if(Simulator.log_started)
//     	                   Simulator.log.updatePump(simulator.PB.pumpState, getName());
//                 	}
//                 	}});
             
//        
        
    }
   
   
   
    
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
    
    
    public final void splash(String imageName) {
        Window splashScreen = new Window(this);
        ImageCanvas canvas = new ImageCanvas(toolkit.getImage(imageName));
        Dimension screenSize = toolkit.getScreenSize();
        Dimension splashSize = canvas.getPreferredSize();
        splashScreen.add(canvas, "Center");
        splashScreen.setLocation(screenSize.width/2 - splashSize.width/2,
                                 screenSize.height/2 - splashSize.height/2);
        splashScreen.setSize(splashSize);
        splashScreen.show();
        splashScreen.toFront();
        try {
            Thread.currentThread().sleep(5000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        splashScreen.dispose();
    }

    public void actionPerformed(ActionEvent event) {
        MenuItem c = (MenuItem)event.getSource();
        String arg = c.getLabel();

       // System.out.println(arg);
        //log.write(arg);
        if(arg.equals("Load Scenario...")) {
            simulator.stop();
            remove(simulator);
            simulator = null;
            System.gc();
            System.runFinalization();
            simulator = new Simulator();
            add(simulator);
            invalidate();
            validate();
            repaint();
        }
        else if(arg.equals("Exit"))
            System.exit(0);
        else if(arg.equals("Start"))
            simulator.start();
        else if(arg.equals("Pause"))
            simulator.pause();
        else if(arg.equals("Resume"))
            simulator.resume();
        else if(arg.equals("Physical"))
            simulator.changeUserInterface(Simulator.PHYSICAL);
        else if(arg.equals("Physical + Functional"))
            simulator.changeUserInterface(Simulator.PHYSICALandFUNCTIONAL);
        else if(arg.equals("Settings Level"))
            simulator.changeUserInterface(Simulator.SETTINGS);
        else if(arg.equals("Flows Level"))
            simulator.changeUserInterface(Simulator.FLOWS);
        else if(arg.equals("Principles Level"))
            simulator.changeUserInterface(Simulator.PRINCIPLES);
        else if(arg.equals("Goals Level"))
            simulator.changeUserInterface(Simulator.GOALS);
       /* else if(arg.equals("Trial Info"))
            simulator.openParameterDialog();*/
        
     
        
    }

    private static Menu makeMenu(Object parent, Object[] items, Object target) {
    	
    	
    	
        Menu m = null;
        if (parent instanceof Menu)
            m = (Menu)parent;
        else if (parent instanceof String)
            m = new Menu((String)parent);
        else
            return null;
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof String) {
                MenuItem mi = new MenuItem((String)items[i]);
                if (target instanceof ActionListener)
                    mi.addActionListener((ActionListener)target);
                m.add(mi);
            }
            else if (items[i] instanceof MenuItem) {
                MenuItem mi = (MenuItem)items[i];
                if (target instanceof ActionListener)
                    mi.addActionListener((ActionListener)target);
                m.add(mi);
            }
            else if (items[i] == null)
                m.addSeparator();
        }
        return m;
    }

}
