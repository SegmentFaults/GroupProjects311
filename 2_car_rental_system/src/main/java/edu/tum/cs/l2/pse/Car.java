package edu.tum.cs.l2.pse;


abstract public class Car {
	private String color;
	private int plateNumber;
	private int capacity;

	public Car(String color, int plateNumber, int capacity) {
		this.color = color;
		this.plateNumber = plateNumber;
		this.capacity = capacity;
	}
	public Car(){
		this.color = "Black";
		this.plateNumber = -1;
		this.capacity = -1;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(int plateNumber) {
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
