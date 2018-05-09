package de.tum.cs.i1.pse.model;

import de.tum.cs.i1.pse.IllegalTemperatureException;

public class TemperatureModel extends java.util.Observable {
	public static double KELVIN_CONST = 273.15;

	private double temperatureK = 0.0;

	public double getF() {
		return ((temperatureK - KELVIN_CONST) * 9.0 / 5.0) + 32.0 ;
	}

	public double getC() {
		return temperatureK - KELVIN_CONST;
	}

	public double getK() {
		return temperatureK;
	}

	public void setF(double tempF) throws IllegalTemperatureException {
		setK((tempF - 32.0) * 5.0 / 9.0 + KELVIN_CONST);
	}

	public void setC(double tempC) throws IllegalTemperatureException {
		setK(tempC + KELVIN_CONST);
	}

	public void setK(double tempK) throws IllegalTemperatureException {
		//TODO: Make sure that a temperature below the absolute zero (o Kelvin) cannot be set in the application in any temperature scale
		if (tempK < 0) //(throw an IllegalTemperatureException)
			throw new IllegalTemperatureException(null);
		else {
			temperatureK = tempK;
			setChanged();
			notifyObservers();
		}
	}


}
