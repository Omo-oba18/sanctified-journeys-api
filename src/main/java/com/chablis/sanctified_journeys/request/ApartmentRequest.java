package com.chablis.sanctified_journeys.request;

import com.chablis.sanctified_journeys.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentRequest {
    private String name;
    private String description;
    private double price;
    private int capacity;
    private List<String> amenities;
    private Address address;
    private String churchName; // Use 'churchName' instead of 'church_id'
}