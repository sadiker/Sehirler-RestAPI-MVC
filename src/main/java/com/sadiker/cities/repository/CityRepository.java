package com.sadiker.cities.repository;

import org.springframework.stereotype.Repository;

import com.sadiker.cities.model.City;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

@Repository
public interface CityRepository extends MongoRepository<City, String> {

    ResponseEntity<City> findByName(String name);

    Optional<City> findByPlate(String plate);

}
