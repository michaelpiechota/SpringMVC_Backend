package com.carmanagement.serviceImplementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.carmanagement.beans.CarBean;
import com.carmanagement.beans.RatingBean;
import com.carmanagement.dao.CarDao;
import com.carmanagement.model.Car;
import com.carmanagement.model.Rating;
import com.carmanagement.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;
	@Override
	public boolean saveCarDetails(CarBean carBean) {
		boolean status = false;
		try{
			Car car = new Car();
			car.setId(carBean.getId());
			car.setMake(carBean.getMake());
			car.setModel(carBean.getModel());
			car.setYear(carBean.getYear());
			carDao.saveCar(car);
		}catch(Exception e){

		}
		return false;
	}

	@Override
	public boolean saveRating(CarBean carBean, RatingBean ratingBean) {

		return false;
	}

	@Override
	public List<CarBean> listOfAllCars() {
		List<CarBean> allCarBeans = null;
		List<Car> allCars = null;
		try{
			allCars =carDao.findAllCars();
			if(!allCars.isEmpty() && allCars!=null) {
				allCarBeans = new ArrayList<CarBean>();
				for (Car car : allCars) {
					CarBean carBean = new CarBean();
					carBean.setId(car.getId());
					carBean.setMake(car.getMake());
					carBean.setModel(car.getModel());
					carBean.setYear(car.getYear());
					if (!car.getRatings().isEmpty() && car.getRatings() != null) {
						Set<RatingBean> ratingBeanSet = new HashSet<RatingBean>();
						Set<Rating> ratingSet = car.getRatings();
						for (Rating rb : ratingSet) {
							RatingBean rt = new RatingBean();
							rt.setId(rb.getId());
							rt.setRating(rb.getRating());
							ratingBeanSet.add(rt);
						}
						carBean.setRatings(ratingBeanSet);
					}
					allCarBeans.add(carBean);
				}
			}
		}catch(Exception e){

		}
		return allCarBeans;
	}

	@Override
	public List<RatingBean> getAllRatings(String id) {
		List<Car> carList = null;
		Set<Rating> ratingList = null;
		List<RatingBean> ratingBeanList = new ArrayList<RatingBean>();
		try{
			carList =	 carDao.findAllRatings(Integer.parseInt(id));
			Car car = carList.get(0);
			ratingList = car.getRatings();
			for(Rating rt : ratingList){
				RatingBean rb = new RatingBean();
				rb.setId(rt.getId());
				rb.setRating(rt.getRating());
				ratingBeanList.add(rb);
			}
		}catch(Exception e){

		}return ratingBeanList;
	}
	@Override
	public boolean updateRating(int id,int rating){
		boolean status = false;
		try{
			System.out.println("ret");
			carDao.upadateCarRating(id,rating);
		}catch(Exception e){

		}
		return status;
	}
}
