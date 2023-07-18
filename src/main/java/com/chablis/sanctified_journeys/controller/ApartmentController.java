package com.chablis.sanctified_journeys.controller;

import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.repository.ApartmentRepository;
import com.chablis.sanctified_journeys.request.ApartmentRequest;
import com.chablis.sanctified_journeys.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/apartments")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    //    search endpoint to get apartment according to parameter
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public List<Apartment> searchApartmentsByLocation(@RequestParam String state, @RequestParam String city) {
        return apartmentService.searchApartmentsByLocation(state, city);
    }

    @GetMapping("/search/filtered")
    @PreAuthorize("isAuthenticated()")
    public List<Apartment> searchApartmentsByLocationAndPriceAndCapacity(
            @RequestParam String state,
            @RequestParam String city,
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam int minCapacity
    ) {
        return apartmentService.searchApartmentsByLocationAndPriceAndCapacity(state, city, minPrice, maxPrice, minCapacity);
    }

    @GetMapping("/search/amenity")
    @PreAuthorize("isAuthenticated()")
    public List<Apartment> searchApartmentsByLocationAndAmenity(
            @RequestParam String state,
            @RequestParam String city,
            @RequestParam String amenity
    ) {
        return apartmentService.searchApartmentsByLocationAndAmenity(state, city, amenity);
    }

    @GetMapping("/search/near-church")
    @PreAuthorize("isAuthenticated()")
    public List<Apartment> findApartmentsNearChurch(
            @RequestParam String churchState,
            @RequestParam String churchCity
    ) {
        return apartmentService.findApartmentsNearChurch(churchState, churchCity);
    }

}
