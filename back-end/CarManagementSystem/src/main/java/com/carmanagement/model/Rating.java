package com.carmanagement.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name="rating")
public class Rating  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "rating")
    @Range(min = 1,max = 10)
    private int rating;
    @ManyToOne
    @JoinColumn(name="car_id",nullable = false)
    private Car car;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Car getCar(){
        return car;
    }

    public void setCar(Car car){
        this.car = car;
    }
}
