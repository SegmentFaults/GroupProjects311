package de.tum.cs.i1.pse;

import org.junit.Assert;
import org.junit.Test;

import de.tum.cs.i1.pse.model.TemperatureModel;


public class FunctionalTest {

	TemperatureModel model = new TemperatureModel();
	Controller controller = new Controller(model);
	double tempC;
	double tempK;
	double tempF;
	double expected = 0, actual = 0;

	@Test(timeout = 100, expected = IllegalTemperatureException.class)
	public void testSetIncorrectK() throws IllegalTemperatureException {
		tempK = -20.5;
		model.setK(tempK);
	}

	@Test(timeout = 100)
	public void checkIfTemperatureIsIncreased() throws IllegalTemperatureException {
		tempC = 27.5;
		model.setC(tempC);
		controller.increaseC();
		expected = 28.5;
		actual = model.getC();
		Assert.assertEquals(expected, actual, 0.0f);
		
		tempF = 80.6;
		model.setF(tempF);
		controller.increaseF();
		expected = 81.6;
		actual = model.getF();
		Assert.assertEquals(expected, actual, 0.01f);
		
		tempK = 300.15;
		model.setK(tempK);
		controller.increaseK();
		expected = 301.15;
		actual = model.getK();
		Assert.assertEquals(expected, actual, 0.0f);		
	}
	
	@Test(timeout = 100)
	public void checkIfTemperatureIsDecreased() throws IllegalTemperatureException {
		tempC = 27.5;
		model.setC(tempC);
		controller.decreaseC();
		expected = 26.5;
		actual = model.getC();
		Assert.assertEquals(expected, actual, 0.0f);
		
		tempF = 80.6;
		model.setF(tempF);
		controller.decreaseF();
		expected = 79.6;
		actual = model.getF();
		Assert.assertEquals(expected, actual, 0.00001f);
		
		tempK = 300.15;
		model.setK(tempK);
		controller.decreaseK();
		expected = 299.15;
		actual = model.getK();
		Assert.assertEquals(expected, actual, 0.0f);		

	}
	
	@Test(timeout = 100)
	public void testUndo() throws IllegalTemperatureException {
		tempC = 0;
		model.setC(tempC);
		controller.decreaseC();
		controller.decreaseC();
		controller.undo();
		expected = -1;
		actual = model.getC();
		Assert.assertEquals(expected, actual, 0.0f);
		
		tempC = 0;
		model.setC(tempC);
		controller.decreaseC();
		controller.increaseC();
		controller.undo();
		expected = -1;
		actual = model.getC();
		Assert.assertEquals(expected, actual, 0.0f);
			
	}
	
	@Test(timeout = 100)
	public void testRedo() throws IllegalTemperatureException {
		tempC = 0;
		model.setC(tempC);
		controller.decreaseC();
		controller.increaseC();
		controller.undo();
		controller.redo();
		expected = 0;
		actual = model.getC();
		Assert.assertEquals(expected, actual, 0.0f);
		
		tempC = 0;
		model.setC(tempC);
		controller.decreaseC();
		controller.increaseC();
		controller.increaseC();
		controller.increaseC();
		controller.undo();
		controller.undo();
		controller.redo();
		expected = 1;
		actual = model.getC();
		Assert.assertEquals(expected, actual, 0.0f);
			
	}

}
