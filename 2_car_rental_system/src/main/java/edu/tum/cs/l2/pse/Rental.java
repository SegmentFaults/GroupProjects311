package edu.tum.cs.l2.pse;

import java.util.Date;

/**
 * TODO: Add the missing method
 * 
 *
 */

public class Rental {
	private Date submissionDate;
	private Date startDate;
	private Date endDate;
	private Person renter;
	private Car car;

	public Rental(Date submissionDate, Date startDate, Date endDate, Person renter, Car car) {
		super();
		this.submissionDate = submissionDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.renter = renter;
		this.car = car;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date date) {
		this.submissionDate = date;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Person getRenter() {
		return renter;
	}

	public void setRenter(Person renter) {
		this.renter = renter;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public boolean isRunning(Date currentDate) {

		if (currentDate.after(startDate) && currentDate.before(endDate))
			return true;
		return false;

	}


}
