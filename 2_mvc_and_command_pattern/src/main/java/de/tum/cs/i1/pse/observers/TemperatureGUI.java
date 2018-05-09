package de.tum.cs.i1.pse.observers;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import de.tum.cs.i1.pse.Controller;
import de.tum.cs.i1.pse.model.TemperatureModel;

public abstract class TemperatureGUI implements java.util.Observer {
	
	@SuppressWarnings("unused")
	private String label;
	protected TemperatureModel model;
	private Controller controller;
	private Frame temperatureFrame;
	private TextField display = new TextField();
	private Button raiseTempButton = new Button("Raise");
	private Button lowerTempButton = new Button("Lower");
	private Button undo = new Button("Undo");
	
	TemperatureGUI(String label, TemperatureModel model, Point location) {
		this.label = label;
		this.model = model;
		temperatureFrame = new Frame(label);
		temperatureFrame.add("North", new Label(label));
		temperatureFrame.add("Center", display);
		Panel buttons = new Panel();
		buttons.add(raiseTempButton);
		buttons.add(lowerTempButton);
		buttons.add(undo);
		temperatureFrame.add("South", buttons);
		temperatureFrame.addWindowListener(new CloseListener());
		model.addObserver(this); // Connect the View to the Model
		temperatureFrame.setSize(200, 200);
		temperatureFrame.setLocation(location);
		temperatureFrame.setVisible(true);
	}
	
	
	TemperatureGUI(String label, Controller controller, Point location) {
		this.label = label;
		this.controller = controller;
		temperatureFrame = new Frame(label);
		temperatureFrame.add("North", new Label(label));
		temperatureFrame.add("Center", display);
		Panel buttons = new Panel();
		buttons.add(raiseTempButton);
		buttons.add(lowerTempButton);
		buttons.add(undo);
		temperatureFrame.add("South", buttons);
		temperatureFrame.addWindowListener(new CloseListener());
		controller.model.addObserver(this); // Connect the View to the Model
		temperatureFrame.setSize(200, 200);
		temperatureFrame.setLocation(location);
		temperatureFrame.setVisible(true);
	}
	
	
	public void show() {
		temperatureFrame.setVisible(true);
	}

	public void setDisplay(String s) {
		display.setText(s);
	}

	public double getDisplay() {
		double result = 0.0;
		try {
			result = Double.valueOf(display.getText()).doubleValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void addDisplayListener(ActionListener a) {
		display.addActionListener(a);
	}

	public void addRaiseTempListener(ActionListener a) {
		raiseTempButton.addActionListener(a);
	}

	public void addLowerTempListener(ActionListener a) {
		lowerTempButton.addActionListener(a);
	}
	
	public void addUndoListener(ActionListener a) {
		undo.addActionListener(a);
	}

	protected TemperatureModel model() {
		return model;
	}

	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
}
