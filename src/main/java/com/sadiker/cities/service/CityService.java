package com.sadiker.cities.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sadiker.cities.model.City;
import com.sadiker.cities.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CityRepository cityRepository;

    // public CityService(CityRepository cityRepository) {
    // this.cityRepository = cityRepository;
    // }

    public ResponseEntity<Object> getByPlate(String plate) {

        // MongoTemplate şablonuyla ;
        City city = mongoTemplate.findOne(Query.query(Criteria.where("plate").regex(".*" + plate + ".*", "i")),
                City.class);
        if (city != null) {
            return ResponseEntity.ok(city);
        } else {
            return ResponseEntity.ok("Bulunamadı..");
        }

    }

    public List<City> findAll() {
        return cityRepository.findAll();

    }

}
