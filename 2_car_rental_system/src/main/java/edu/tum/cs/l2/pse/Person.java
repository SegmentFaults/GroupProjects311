package edu.tum.cs.l2.pse;

import java.util.Date;

public class Person{
    private String name;
    private String drivingLicenceNumber;
    private Date dateOfBirth;

    public Person(String name, String drivingLicenceNumber, Date dateOfBirth) {
        this.name = name;
        this.drivingLicenceNumber = drivingLicenceNumber;
        this.dateOfBirth = dateOfBirth;
    }
    public Person(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDrivingLisenceNumber(){
        return this.drivingLicenceNumber;
    }

    public void setDrivingLisenceNumber(String drivingLicenceNumber){
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}