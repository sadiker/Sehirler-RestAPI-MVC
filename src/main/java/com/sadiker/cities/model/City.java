package com.sadiker.cities.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "sehirler")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {
    
    @Id
    private String id;
    private String name;
    private String plate;
    private String image;
    private String region ;


}
