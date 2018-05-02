package de.tum.cs.i1.pse.observers;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import de.tum.cs.i1.pse.model.TemperatureModel;

// Class implemented for Kelvin
public class KelvinGUI extends TemperatureGUI {
	
	public KelvinGUI(TemperatureModel model, Point location) {
		super("Kelvin Temperature", model, location);
		setDisplay("" + model.getK());
		addRaiseTempListener(new RaiseTempListener());
		addLowerTempListener(new LowerTempListener());
		addDisplayListener(new DisplayListener());
	}

	public void update(Observable t, Object o) { // Called from the Model
		setDisplay("" + model().getK());
	}

	class RaiseTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setK(model().getK() + 1.0);
		}
	}

	class LowerTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setK(model().getK() - 1.0);
		}
	}

	class DisplayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double value = getDisplay();
			model().setK(value);
		}
	}
}
