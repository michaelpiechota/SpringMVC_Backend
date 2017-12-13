package com.carmanagement.dao;

import com.carmanagement.model.Car;
import com.carmanagement.model.Rating;

import java.util.List;

public interface CarDao {
    public boolean saveCar(Car car);
    public List<Car> findAllCars();
    public List<Car> findAllRatings(int id);
    public boolean upadateCarRating(int id,int rating);
}
