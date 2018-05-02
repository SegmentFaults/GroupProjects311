package edu.tum.cs.l2.pse;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class FunctionTest {

	Date date;
	Calendar cal1 = new GregorianCalendar();
	Calendar cal2 = new GregorianCalendar();
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	
	@Test(timeout=100)
	public void testSaveRental() {
		
		try {
			date = sdf.parse("22102016");
			cal1.setTime(date);
			date = sdf.parse("24102016");
			cal2.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Car rentedCar = new SportCar();
		((SportCar) rentedCar).setSuperCharger(true);
		Rental R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), new Person("Abc"), rentedCar);
		CarRentalSystem crs = new CarRentalSystem();
		crs.getRentals().clear();
		crs.saveRental(R1);
		assertEquals("saveRental method is not implemented correctly",crs.getRentals().size(),1);
	}

	@Test(timeout=100)
	public void testComputeExpense(){
		try {
			date = sdf.parse("22102016");
			cal1.setTime(date);
			date = sdf.parse("24102016");
			cal2.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//test case for SportCar with supercharger.
		Car rentedCar = new SportCar();
		((SportCar) rentedCar).setSuperCharger(true);
		Rental R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), new Person("Arpit"), rentedCar);
		assertEquals("computeExpense method is not implemented correctly",36,R1.computeExpense(),0.001);
		
		//test case for SportCar without supercharger.
		 rentedCar = new SportCar();
		 R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), new Person("Arpit"), rentedCar);
		assertEquals("computeExpense method is not implemented correctly",30,R1.computeExpense(),0.001);
		
		//test case for familyCar with extraSeat.
		 rentedCar = new FamilyCar();
		((FamilyCar) rentedCar).setChildSeat(true);
		 R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), new Person("Arpit"), rentedCar);
		 assertEquals("computeExpense method is not implemented correctly",27,R1.computeExpense(),0.001);
	
		//test case for familyCar without extraSeat.
		 rentedCar = new FamilyCar();
		((FamilyCar) rentedCar).setChildSeat(false);
		 R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), new Person("Arpit"), rentedCar);
		 assertEquals("computeExpense method is not implemented correctly",24,R1.computeExpense(),0.001);
		 
		//test case for SUVCar without terrainMode.
		 rentedCar = new SUVCar();
		 R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), new Person("Arpit"), rentedCar);
		 assertEquals("computeExpense method is not implemented correctly",36,R1.computeExpense(),0.001);
	
	}
	
	@Test(expected=RuntimeException.class, timeout=100)
	public void tesExistingRental() {
		try {
			date = sdf.parse("22102016");
			cal1.setTime(date);
			date = sdf.parse("24112016");
			cal2.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Car rentedCar = new SportCar();
		((SportCar) rentedCar).setSuperCharger(true);
		Person testPerson = new Person("Test");
		Rental R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(),testPerson , rentedCar);
		CarRentalSystem crs = new CarRentalSystem();
		crs.saveRental(R1);
		try {
			date = sdf.parse("01112016");
			cal1.setTime(date);
			date = sdf.parse("03112016");
			cal2.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Rental R2 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), testPerson, rentedCar);
		crs.saveRental(R2);
	   
	}

}
