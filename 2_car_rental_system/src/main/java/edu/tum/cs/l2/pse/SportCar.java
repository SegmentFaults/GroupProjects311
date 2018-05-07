package edu.tum.cs.l2.pse;
/*
* @Author Michael Gentile
* Project #4
* 6 May 2018
* Source https://github.com/togiberlin/java_design_pattern_koans
* This class is made by me. 
*/

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