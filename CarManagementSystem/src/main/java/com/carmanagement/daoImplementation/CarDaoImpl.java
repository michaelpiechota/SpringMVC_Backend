package com.carmanagement.daoImplementation;

import com.carmanagement.dao.CarDao;
import com.carmanagement.model.Car;
import com.carmanagement.model.Rating;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CarDaoImpl implements CarDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveCar(Car car) {
        boolean flag = false;
        try {
            System.out.println("one");

            entityManager.persist(car);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Car> findAllCars() {
        List<Car> allCarList = null;
        try {
            javax.persistence.Query query = entityManager.createQuery("from Car");
            allCarList = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return allCarList;
    }

    @Override
    public List<Car> findAllRatings(int tempid) {
        List<Car> ratingAllList = null;
        try {
            javax.persistence.Query query = entityManager.createQuery("from Car where id = :tempid");
            query.setParameter("tempid",tempid);
            ratingAllList = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ratingAllList;
    }
    @Override
    public boolean upadateCarRating(int id,int rating){
        boolean status = false;
        try{
            System.out.println("one "+rating);
            javax.persistence.Query query = entityManager.createQuery("from Car where id = :tempid");
            query.setParameter("tempid",id);
            List<Car> car =  query.getResultList();
            System.out.println("two");
            Car carTemp = car.get(0);
            Rating rt = new Rating();
            rt.setRating(rating);
            rt.setCar(carTemp);
            entityManager.persist(rt);
            System.out.println("one");
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }
}
