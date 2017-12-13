package com.carmanagement.service;

import java.util.List;

import com.carmanagement.beans.CarBean;
import com.carmanagement.beans.RatingBean;

public interface CarService {
	public boolean saveCarDetails(CarBean carBean);
	public boolean saveRating(CarBean carBean,RatingBean ratingBean);
	public List<CarBean> listOfAllCars();
	public List<RatingBean> getAllRatings(String id);
	public boolean updateRating(int id,int rating);
}
