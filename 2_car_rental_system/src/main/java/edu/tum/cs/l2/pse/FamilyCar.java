package edu.tum.cs.l2.pse;
/*
* @Author Michael Gentile
* Project #4
* 6 May 2018
* Source https://github.com/togiberlin/java_design_pattern_koans
* This class is original by me. 
*/

public class FamilyCar extends Car{
    private boolean childSeat;
    public FamilyCar(String color, int plateNumber, int capacity, boolean childSeat) {
        super(color, plateNumber, capacity);
        this.childSeat = childSeat;
    }
    public FamilyCar(){
        super();
        this.childSeat = false;
    }
    public int getDailyCost(){
        if (childSeat){
            return 20;
        }
        else {
            return 12;
        }
    }

    public boolean hasChildSeat(){
        return childSeat;
    }

    public void setChildSeat(boolean childSeat){
        this.childSeat = childSeat;
    }
}