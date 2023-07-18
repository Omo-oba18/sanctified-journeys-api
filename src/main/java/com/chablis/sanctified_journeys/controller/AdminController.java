package com.chablis.sanctified_journeys.controller;

import com.chablis.sanctified_journeys.config.JWTService;
import com.chablis.sanctified_journeys.model.Address;
import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.model.Church;
import com.chablis.sanctified_journeys.request.ApartmentRequest;
import com.chablis.sanctified_journeys.service.ApartmentService;
import com.chablis.sanctified_journeys.service.ChurchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final ChurchService churchService;
    private final ApartmentService apartmentService;
    private final JWTService jwtService;

    @PostMapping("/church")
    public ResponseEntity<?> createChurch(@RequestBody Church church) {
        try {
            Church createdChurch = churchService.createChurch(church);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChurch);
        } catch (RuntimeException ex) {
            // Handle the exception and return an appropriate response with the error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

        }

    }

    //    post endpoints to create an apartment
    @PostMapping("/apartment")
    public ResponseEntity<?> createApartment(@RequestBody ApartmentRequest request) {
        try {
            // Assuming you have ApartmentRequest class to hold the request data
            // Extract data from the request and create an Address and Church objects
            Address address = new Address(request.getStreet(), request.getCity(), request.getState());
            Church church = churchService.getChurchById(request.getChurchId());

            // Create the apartment using the ApartmentService and associate it with the church
            Apartment apartment = apartmentService.createApartment(
                    request.getName(),
                    request.getDescription(),
                    request.getPrice(),
                    request.getCapacity(),
                    request.getAmenities(),
                    address,
                    church
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(apartment);
        } catch (RuntimeException ex) {
            // Handle the exception and return an appropriate response with the error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

        }

    }
}