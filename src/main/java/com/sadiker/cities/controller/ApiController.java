package com.sadiker.cities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sadiker.cities.model.City;
import com.sadiker.cities.service.CityService;

@RestController
public class ApiController {
    @Autowired
    CityService cityService;

    @GetMapping("/cities")
    public List<City> getAll() {
        return cityService.findAll();
    }

    @GetMapping("/helloworld")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Merhaba Dünya!!!");
    }

    @GetMapping("/cities/{plate}")
    public ResponseEntity<Object> getByPlate(@PathVariable("plate") String plate) {
        return cityService.getByPlate(plate);
    }

}
