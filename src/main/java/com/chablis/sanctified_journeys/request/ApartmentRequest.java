package com.chablis.sanctified_journeys.request;

import com.chablis.sanctified_journeys.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentRequest {
    private String name;
    private String description;
    private double price;
    private int capacity;
    private List<String> amenities;
    private String street;
    private String city;
    private String state;
    private Long churchId;

}
