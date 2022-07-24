
Duress II
Modified by: 	Signe Bray
		Advanced Interface Design Lab
		University of Waterloo
Date:		April, 2003

Major Changes:
----------------------
1) Logging

A logging feature was added, tolog the changes made by the user to the interface, as well as 
steady state information, and the reason for any simulation terminations. When a simulation 
is started, the user is prompted to enter a 'User Name' and 'Trial #', the log files are stored 
in a 'log_files' directory, with the name log_<user name>_<trial #>.txt.
If a log file already exists for a given set of parameters, the user is prompted to either 
overwrite the existing log file or re-enter the information.

2) P Interface demand lines

The green demand bars on the P interface were moved closer to the meters, to make it easier 
to see when the slider control is in the range of the demand.

3) Slider Granularity

The sliders on the interface were on a scale from 1 to 10, with only integer values selectable. 
In some situations, this did not allow for fine enough control. The sliders were modified so that 
their values are actually from 1 to 100, though they outwardly appear to be from 1 to 10. This 
allows for a single decimal place of precision in the slider values.

4) Setting Output Temperature

A line in Reservoir.java was originally commented out, that prevented the simulation from reaching 
steady state, since it prevented the updating of the output temperature. Therefore the line:

	setTemperatureOut(temperature);       //Note:  last minute fix  (be my guest)

was uncommented.

Files modified:
--------------------

Simulator.java
	Modifications: 
	1 - added a Log member to the Simulator class
	2 - added a startLog method to start a log file with the initial settings
	3 - when a simulation is  started opens dialog to prompt for log information,
	    uses ParamDialog class
	4 - writes the reason for simulation termination to log file
	5 - changed errorDialog to terminationDialog

Reservoir.java
	Modifications:
	1 - changed ValveLabelCanvas member to the inheriting ValbeSpecialLabelCanvas
	    in order to move the demand bar closer to the meter
	2 - added writing to a log when settings are changes
	3 - adjusted scale of sliders to add precision, they still appear
	    to be 0-10 although in actuality they are 0-100

Heater.java
	Modifications:
	1 - added getName method and writing changes to log file
	2 - modified scale of sliders for precision

Pump.java
	Modifications:
	1 - added writing to log file 

Valve.java
	Modifications:
	1 - modified to increase precision of sliders
	2 - added writing to log file 
	3 - moved valvelabelcanvas to separate file

Files added:
-----------------

Log.java
	This class defines a log file for recording the changes
	made by users of the software.

ValveLabelCanvas.java
	Moved this out of Valve.java, so that ValveSpeciallLabelCanvas, defined in Reservoir.java,
	can inherit from this class. This change was made in order to move the demand lines on the 
	P interface closer to the sliders.