package de.tum.cs.i1.pse;

import java.awt.Point;

import de.tum.cs.i1.pse.model.TemperatureModel;
import de.tum.cs.i1.pse.observers.CelsiusGUI;
import de.tum.cs.i1.pse.observers.FahrenheitGUI;
import de.tum.cs.i1.pse.observers.GraphGUI;
import de.tum.cs.i1.pse.observers.KelvinGUI;
import de.tum.cs.i1.pse.observers.SliderGUI;

public class Runner {

	public static void main(String[] args) {
		TemperatureModel  temperature = new TemperatureModel();
		Controller controller = new Controller(temperature);
		
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
