package com.sadiker.cities.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
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

    @Cacheable(value = "city", key = "#plate")
    public ResponseEntity<Object> getByPlate(String plate) {

        // MongoTemplate şablonuyla ;
        City city = mongoTemplate.findOne(Query.query(Criteria.where("plate").regex(plate, "i")),
                City.class);

        if (city != null) {
            return new ResponseEntity<>(city,HttpStatus.OK);
        } 
            return new ResponseEntity<>("Bulunamadı...",HttpStatus.BAD_REQUEST);
   

    }

    @Cacheable("cities")
    public List<City> findAll() {
        return cityRepository.findAll();

    }

    @Caching(evict = {
            @CacheEvict(value = "city", key = "#plate"),
            @CacheEvict(value = "cities", allEntries = true),
    })
    public String deleteByPlate(String plate) {
        if (cityRepository.findByPlate(plate).isPresent()) {
            cityRepository.delete(cityRepository.findByPlate(plate).get());
            return "Silindi...";
        }
        return "Bulunamadı...";
    }

}
