package de.tum.cs.i1.pse.observers;
 

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import de.tum.cs.i1.pse.model.TemperatureModel;

public class CelsiusGUI extends TemperatureGUI {

	public CelsiusGUI(TemperatureModel model, Point location) {
		super("Celsius Temperature", model, location);
		setDisplay("" + model.getC());
		addRaiseTempListener(new RaiseTempListener());
		addLowerTempListener(new LowerTempListener());
		addDisplayListener(new DisplayListener());
	}

	public void update(Observable t, Object o) { // Called from the Model
		setDisplay("" + model().getC());
	}

	class RaiseTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setC(model().getC() + 1.0);
		}
	}

	class LowerTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setC(model().getC() - 1.0);
		}
	}

	class DisplayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double value = getDisplay();
			model().setC(value);
		}
	}
}