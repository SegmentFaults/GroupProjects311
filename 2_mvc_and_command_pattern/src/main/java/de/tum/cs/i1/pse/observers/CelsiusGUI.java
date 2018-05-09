package de.tum.cs.i1.pse.observers;
 

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import de.tum.cs.i1.pse.Controller;
import de.tum.cs.i1.pse.IllegalTemperatureException;
import de.tum.cs.i1.pse.model.TemperatureModel;

public class CelsiusGUI extends TemperatureGUI {

	Controller controller;
	
	public CelsiusGUI(TemperatureModel model, Point location) {
		super("Celsius Temperature", model, location);
		setDisplay("" + model.getC());
		addRaiseTempListener(new RaiseTempListener());
		addLowerTempListener(new LowerTempListener());
		addDisplayListener(new DisplayListener());
	}
	
	public CelsiusGUI(Controller controller, Point location) {
		super("Celsius Temperature2", controller, location);
		this.controller = controller;
		this.model = controller.model;
		setDisplay("" + controller.getC());
		addRaiseTempListener(new RaiseTempListener());
		addLowerTempListener(new LowerTempListener());
		addUndoListener(new UndoListener());
		addDisplayListener(new DisplayListener());
	}

	public void update(Observable t, Object o) { // Called from the Model
		setDisplay("" + model().getC());
	}

	class RaiseTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				model().setC(model().getC() + 1.0);
				// controller.increaseC();
			} catch (IllegalTemperatureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class LowerTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				model().setC(model().getC() - 1.0);
				// controller.decreaseC();
			} catch (IllegalTemperatureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class UndoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				model().setC(model().getC() - 1.0);
				// controller.undo();
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
				model().setC(value);
				// controller.setC(value);
			} catch (IllegalTemperatureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}