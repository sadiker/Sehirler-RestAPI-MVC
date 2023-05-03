package com.sadiker.cities;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sadiker.cities.controller.ApiController;
import com.sadiker.cities.model.City;
import com.sadiker.cities.service.CityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiControllerTest {

    @Mock
    CityService cityService;

    @InjectMocks
    ApiController apiController;

    @Test
    void getAll() {

        City city = new City();
        city.setImage("Resim");
        city.setName("İsim");
        city.setPlate("10");
        city.setRegion("Bölge");

        List<City> expected = new ArrayList<City>();
        expected.add(city);

        when(cityService.findAll()).thenReturn(expected);

        List<City> response = apiController.getAll();
        List<City> actual = response;

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected.get(0).getName(), actual.get(0).getName()));
    }

    @Test
    void getByPlate() {

        City city = new City();
        city.setPlate("30");

        when(cityService.getByPlate(anyString())).thenReturn(ResponseEntity.ok(city));

        ResponseEntity<Object> response = apiController.getByPlate("30");
        City actual = (City) response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(city.getPlate(), actual.getPlate()));

    }

    @Test
    void helloWorld(){

        String expectedString="Merhaba Dünya!!!";

        String actualString =apiController.helloWorld().getBody();

        assertEquals(expectedString, actualString);
       

    }
}
