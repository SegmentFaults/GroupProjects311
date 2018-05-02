package de.tum.cs.i1.pse;

import java.awt.Point;

import de.tum.cs.i1.pse.model.TemperatureModel;
import de.tum.cs.i1.pse.observers.CelsiusGUI;
import de.tum.cs.i1.pse.observers.FahrenheitGUI;
import de.tum.cs.i1.pse.observers.GraphGUI;
import de.tum.cs.i1.pse.observers.KelvinGUI;
import de.tum.cs.i1.pse.observers.SliderGUI;

public class MVCTemperatureConverter {
	
	public static void main(String args[]) {
		TemperatureModel temperature = new TemperatureModel();
		
		//TODO: Refactor Controller into separate class and initialize it
		//TODO: In init() function in Controller add different Temperature GUIs as Observers for the Model
		//TODO: In Controller implement methods to set, increase and decrease of all Temperature scales. Use these methods in Temperature GUIs
		
		//TODO: In Controller delegate the above functionality to the Invoker, following the Command Pattern.
		//TODO: Create 3 different Command classes representing each temperature scale for both Raise and Lower temperature commands (LowerKCommand, LowerCCommand etc.)
		//TODO: Provide the implementation for the menu commands Raise temperature, Lower temperature, Undo last command and Redo last Command.
		//TODO: In Controller create methods to redo and undo actions, delegate them to the Invoker. 
		//TODO: Add the Control Panel to the View
		       
		
		FahrenheitGUI fahrenheitGUI = new FahrenheitGUI(temperature, new Point(100, 100));
		fahrenheitGUI.show();

		CelsiusGUI celsiusGUI = new CelsiusGUI(temperature, new Point(100, 300));
		celsiusGUI.show();

		SliderGUI sliderGUI = new SliderGUI(temperature, new Point(500, 100));
		sliderGUI.show();

		GraphGUI gaugeGUI = new GraphGUI(temperature, new Point(500, 200));
		gaugeGUI.setVisible(true);

		// added for Kelvin
		KelvinGUI kelvinGUI = new KelvinGUI(temperature, new Point(300, 100));
		kelvinGUI.show();
		
	}
}
