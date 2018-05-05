package edu.tum.cs.l2.pse;




/*
 * TODO: add the missing attributes and methods
 * TODO: Add the missing abstract method
 * TODO: Create the missing subclasses
 *
 */

abstract public class Car {
	private String color;
	private int plateNumber;
	private int capacity;

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public int getCapacity(){
		return this.capacity;
	}
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	abstract public int getDailyCost();
	public String showInformation(){
		return color + " " + plateNumber + " " + capacity;
	}
}
