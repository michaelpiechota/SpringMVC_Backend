package com.carmanagement.beans;

import java.util.Set;


public class CarBean {
	 private int id;
	 private String make;
	 private String model;
	 private int year;
	 private Set<RatingBean> ratings;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Set<RatingBean> getRatings() {
		return ratings;
	}
	public void setRatings(Set<RatingBean> ratings) {
		this.ratings = ratings;
	}
	@Override
	public String toString() {
		return "CarBean [id=" + id + ", make=" + make + ", model=" + model
				+ ", year=" + year + ", ratings=" + ratings + "]";
	}
}
