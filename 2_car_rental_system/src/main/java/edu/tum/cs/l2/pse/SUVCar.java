package edu.tum.cs.l2.pse;
/*
* @Author Michael Gentile
* Project #4
* 6 May 2018
* Source https://github.com/togiberlin/java_design_pattern_koans
* This class is made by me. 
* This class is another extension of the car with a terrain mode. 
*/

public class SUVCar extends Car{
    private boolean terrainMode;
    public SUVCar(String color, int plateNumber, int capacity, boolean terrainMode) {
        super(color, plateNumber, capacity);
        this.terrainMode = terrainMode;
    }
    public SUVCar(){
        super();
        this.terrainMode = false;
    }
    public int getDailyCost(){
        if (terrainMode){
            return 30;
        }
        else {
            return 18;
        }
    }

    public boolean hasTerrainMode(){
        return terrainMode;
    }

    public void setTerrainMode(boolean terrainMode){
        this.terrainMode = terrainMode;
    }
}
