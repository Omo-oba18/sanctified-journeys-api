package com.chablis.sanctified_journeys.request;

import com.chablis.sanctified_journeys.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentRequest {
    private boolean availability;
    private float price;
    private Address address;
}
