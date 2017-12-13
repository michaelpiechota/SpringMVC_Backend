package com.carmanagement.controller;

import com.carmanagement.beans.CarBean;
import com.carmanagement.beans.RatingBean;
import com.carmanagement.model.Rating;
import com.carmanagement.service.CarService;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/carmanager/")
public class RequestController {

    @Autowired
    private CarService carService;
    @GetMapping(value = "/saveCar/{make}/{model}/{year}",consumes = "application/json")
    public ResponseEntity<?> addCar(@PathVariable String make,@PathVariable String model,@PathVariable String year){
        ResponseEntity<?> responseEntity = null;
        String status = "";
        boolean flag = false;
        try{
            CarBean carObject = new CarBean();
            carObject.setMake(make);
            carObject.setModel(model);
            carObject.setYear(Integer.parseInt(year));
            flag = carService.saveCarDetails(carObject);
            responseEntity = new ResponseEntity<CarBean>(HttpStatus.OK);
        }catch(Exception e){
            responseEntity = new ResponseEntity<CarBean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping(value = "/addRating/{id}/{rating}")
    public String addRatingToCar(@PathVariable String id,@PathVariable String rating){
        String status = "";
        try{
            carService.updateRating(Integer.parseInt(id),Integer.parseInt(rating));
        }catch(Exception e){
        }
        return status;
    }
    @GetMapping("/getRatings/{id}")
    public @ResponseBody String getRatingToCar(@PathVariable String id){
        String status = "";
        JSONArray jsArray = null;
        List<RatingBean> ratingList = null;
        try{
            ratingList = carService.getAllRatings(id);
            jsArray = new JSONArray(ratingList);
            System.out.println(jsArray.toString());
        }catch(Exception e){

        }
        return jsArray.toString();
    }
    @GetMapping(value = "/getAllCars/",produces = "application/json")
    public @ResponseBody String getAllCars(){
        List<CarBean> carList = null;
        JSONArray jsArray = null;
        try{
            carList = carService.listOfAllCars();
            jsArray = new JSONArray(carList);
        }catch(Exception e){

        }return jsArray.toString();
    }

    @GetMapping(value = "/logOut")
    public void logOut(HttpServletResponse response,HttpSession session,HttpServletRequest request){
        try {
            if (session != null) {
                String sessionId = session.getId();
                session.invalidate();
                Cookie cookies[] = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (sessionId.equalsIgnoreCase(cookie.getValue())||cookie.getName().equalsIgnoreCase("XSRF-TOKEN")) {
                        cookie.setMaxAge(0);
                        cookie.setValue(null);
                        cookie.setDomain(request.getServerName());
                        cookie.setPath(request.getServletContext().getContextPath() + "/");
                        cookie.setSecure(request.isSecure());
                        response.addCookie(cookie);
                    }
                }
                response.sendRedirect("http://localhost:4200/login");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
