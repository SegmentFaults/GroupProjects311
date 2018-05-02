package edu.tum.cs.l2.pse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * TODO: Create the missing Person class.
 *
 * 
 *
 */

public class CarRentalSystem {
	private ArrayList<Rental> rentals = new ArrayList<Rental>();
	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<Person> users = new ArrayList<Person>();

	public void addCar(Car car) {
		cars.add(car);
	}

	public void removeCar(Car car) {
		cars.remove(car);
	}

	public ArrayList<Car> listCars() {
		return cars;
	}

	public void registerUser(Person user) {
		users.add(user);
	}

	public void removeUser(Person user) {
		users.remove(user);
	}

	public ArrayList<Person> listUsers() {
		return users;
	}

	public void saveRental(Rental r) {
		// TODO: Implement the saveRental() method according to the problem statement. 
	}

	public ArrayList<Rental> getRentals() {
		return rentals;
	}

	public ArrayList<Rental> getRentals(Person person) {
		ArrayList<Rental> temp = new ArrayList<Rental>();
		for (int i = 0; i < rentals.size(); i++) {
			if (((Rental) rentals.get(i)).getRenter() == person)
				temp.add(rentals.get(i));
		}
		return temp;
	}

	public ArrayList<Rental> getRentals(Car car) {
		ArrayList<Rental> temp = new ArrayList<Rental>();
		for (int i = 0; i < rentals.size(); i++) {
			if (((Rental) rentals.get(i)).getCar() == car)
				temp.add(rentals.get(i));
		}
		return temp;
	}

	public static void main(String args[]) {

		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

		Date date;
		try {
			date = sdf.parse("25102016");
			cal1.setTime(date);
			date = sdf.parse("27102016");
			cal2.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Car rentedCar = new SportCar();
		Rental R1 = new Rental(cal1.getTime(), cal1.getTime(), cal2.getTime(), new Person("Arpit"), rentedCar);
		System.out.println(R1.computeExpense());
		CarRentalSystem crs = new CarRentalSystem();
		crs.saveRental(R1);

	}

}
