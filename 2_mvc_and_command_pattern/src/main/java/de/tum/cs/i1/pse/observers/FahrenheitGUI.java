package de.tum.cs.i1.pse.observers;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import de.tum.cs.i1.pse.Controller;
import de.tum.cs.i1.pse.IllegalTemperatureException;
import de.tum.cs.i1.pse.model.TemperatureModel;

public class FahrenheitGUI extends TemperatureGUI {
	
	Controller controller;
	
	public FahrenheitGUI(TemperatureModel model, Point location) {
		super("Fahrenheit Temperature", model, location);
		setDisplay("" + model.getF());
		addRaiseTempListener(new RaiseTempListener());
		addLowerTempListener(new LowerTempListener());
		addDisplayListener(new DisplayListener());
	}
	public FahrenheitGUI(Controller controller, Point location) {
		super("Fahrenheit Temperature", controller, location);
		setDisplay("" + controller.getF());
		addRaiseTempListener(new RaiseTempListener());
		addLowerTempListener(new LowerTempListener());
		addDisplayListener(new DisplayListener());
	}

	public void update(Observable t, Object o) { // Called from the Model
		setDisplay("" + model().getF());
	}

	class RaiseTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				model().setF(model().getF() + 1.0);
			} catch (IllegalTemperatureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class LowerTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				model().setF(model().getF() - 1.0);
			} catch (IllegalTemperatureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class DisplayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double value = getDisplay();
			try {
				model().setF(value);
			} catch (IllegalTemperatureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
