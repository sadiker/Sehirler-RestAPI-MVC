package com.sadiker.cities;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sadiker.cities.model.City;
import com.sadiker.cities.repository.CityRepository;
import com.sadiker.cities.service.CityService;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @InjectMocks
    CityService cityService;
    @Mock
    CityRepository cityRepository;
    @Mock
    MongoTemplate mongoTemplate;

    @Test
    void getByPlate_exist() {
        // koşullar
        City expectedCity = City.builder()
                .name("Şehir")
                .plate("100")
                .region("Bölge")
                .build();

        // test metodu
        when(mongoTemplate.findOne(Query.query(Criteria.where("plate").regex("100", "i")),
                City.class)).thenReturn(expectedCity);

        ResponseEntity<Object> response = cityService.getByPlate("100");
        City actualCity = (City) response.getBody();

        // sonuç
        Assertions.assertNotNull(expectedCity);
        Assertions.assertEquals(expectedCity.getName(), actualCity.getName());
        Assertions.assertEquals(expectedCity, actualCity);

    }

    @ParameterizedTest(name = "Plaka:{0}")
    @ValueSource(strings = { "35", "06", "34" })
    void getByPlate_notExistOrExist(String givenString) {

        String plateString = givenString;
        ResponseEntity<Object> responseNull, responseNotNull;
        City actualCity;

        City expectedCity = City.builder()
                .name("Şehir")
                .plate("06")
                .region("Bölge")
                .build();

        // o plakada şehir varsa ;
        if (plateString.equals("06")) {
            when(mongoTemplate.findOne(Query.query(Criteria.where("plate").regex(plateString, "i")),
                    City.class)).thenReturn(expectedCity);
            responseNotNull = cityService.getByPlate("06");
            actualCity = (City) responseNotNull.getBody();

            Assertions.assertEquals(expectedCity, actualCity);
            Assertions.assertEquals(HttpStatus.OK,responseNotNull.getStatusCode());
            Assertions.assertEquals(expectedCity.getPlate(), actualCity.getPlate());
        }

        // o plakada şehir yoksa ;
        when(mongoTemplate.findOne(Query.query(Criteria.where("plate").regex(plateString, "i")),
                City.class)).thenReturn(null);
        responseNull = cityService.getByPlate(plateString);

        // sonuç
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseNull.getStatusCode());
        Assertions.assertEquals("Bulunamadı...", responseNull.getBody());
    }

   

    
    @ParameterizedTest(name = "Plaka:{0}")
    @ValueSource(strings = { "35", "06", "34" })
    void deleteByPlate_checkExistOrNotExist(String givenString) {

        String plateString =givenString;
        String response;

        City expectedCity = City.builder().name("İstanbul").plate("34").build();

        if (plateString.equals("34")) {
            when(cityRepository.findByPlate(plateString)).thenReturn(Optional.of(expectedCity));

            response = cityService.deleteByPlate("34");

            Assertions.assertEquals("Silindi...", response);
        }

   
        when(cityRepository.findByPlate(anyString())).thenReturn(Optional.empty());
            response = cityService.deleteByPlate(plateString);
        Assertions.assertEquals("Bulunamadı...", response);

       

    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Test işlemi başladı..");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Test işlemi bitti..");
    }
}
