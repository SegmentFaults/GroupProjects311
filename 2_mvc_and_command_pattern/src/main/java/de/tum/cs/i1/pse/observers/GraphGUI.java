package de.tum.cs.i1.pse.observers;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import de.tum.cs.i1.pse.model.TemperatureModel;

@SuppressWarnings("serial")
public class GraphGUI extends Frame implements Observer {

	private int max = 150;
	private int min = -20;
	private int currentValue; 
	
	private TemperatureModel model;
	private Canvas gaugeCanvas;
	
	
	public GraphGUI(TemperatureModel model, Point location) {
		super("Temperature Gauge");
		this.model = model;
		Panel Top = new Panel();
		add("North", Top);
		gaugeCanvas = new GaugeCanvas();
		gaugeCanvas.setSize(500, 280);
		add("Center", gaugeCanvas);
		setSize(250, 350);
		setLocation(location);
		setVisible(true);
		model.addObserver(this); // Connect to the model
	}

	public void update(Observable obs, Object o) { // Respond to changes
		repaint();
	}

	public void paint(Graphics g) {
		int celsius = (int) model.getC(); // Use the current data to paint
		setCurrentValue(celsius);
		gaugeCanvas.repaint();
		super.paint(g);
	}
	
	public void setCurrentValue(int level) {
		this.currentValue = level;
	}

	public int getCurrentValue() {
		return this.currentValue;
	}

	

	private class GaugeCanvas extends Canvas {

		private Color fillcolor;
		private static final int width = 20;
		private static final int top = 20;
		private static final int height = 200;
		private static final int left = 100;

		public void paint(Graphics g) {
			g.setColor(Color.black);
			g.drawRect(left, top, width, height);
			if (getCurrentValue() > 0.0)
				fillcolor = Color.red;
			else
				fillcolor = Color.blue;
			g.setColor(fillcolor);
			g.fillOval(left - width / 2, top + height - width / 3, width * 2, width * 2);
			g.setColor(Color.black);
			g.drawOval(left - width / 2, top + height - width / 3, width * 2, width * 2);
			g.setColor(Color.white);
			g.fillRect(left + 1, top + 1, width - 1, height - 1);
			g.setColor(fillcolor);
			long redtop = height * (getCurrentValue() - max) / (min - max);
			g.fillRect(left + 1, top + (int) redtop, width - 1, height - (int) redtop);
		}
	}
}
