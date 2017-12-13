package com.carmanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DummyController {
    @GetMapping(value = "/")
    public void redirectToDetailsPage(HttpServletResponse response){
        try {
            response.sendRedirect("http://localhost:4200/cardetails");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
