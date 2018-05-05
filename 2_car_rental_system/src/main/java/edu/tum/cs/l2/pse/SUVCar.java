package edu.tum.cs.l2.pse;

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