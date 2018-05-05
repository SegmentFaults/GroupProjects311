package edu.tum.cs.l2.pse;

public class SportCar extends Car{
    private boolean superCharger;
    SportCar(String color, int plateNumber, int capacity, boolean superCharger) {
        super(color, plateNumber, capacity);
        this.superCharger = superCharger;
    }
    SportCar(){
        super();
        this.superCharger = false;
    }
    public int getDailyCost(){
        if (superCharger){
            return 18;
        }
        else {
            return 15;
        }
    }

    public boolean hasSuperCharger(){
        return superCharger;
    }

    public void setSuperCharger(boolean superCharger){
        this.superCharger = superCharger;
    }
}