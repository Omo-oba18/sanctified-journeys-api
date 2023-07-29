package com.chablis.sanctified_journeys.controller;

import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.model.Church;
import com.chablis.sanctified_journeys.request.ApartmentRequest;
import com.chablis.sanctified_journeys.response.EntityResponse;
import com.chablis.sanctified_journeys.service.ApartmentService;
import com.chablis.sanctified_journeys.service.ChurchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final ChurchService churchService;
    private final ApartmentService apartmentService;

    @PostMapping("/church")
    public ResponseEntity<EntityResponse> createChurch(@RequestBody Church church) {
        try {
            Church createdChurch = churchService.createChurch(church);
            String successMessage = "Church " + createdChurch.getName() + " created successfully";
            EntityResponse response = EntityResponse.builder()
                    .success(true)
                    .message(successMessage)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException ex) {
            // Handle the exception and return an appropriate response with the error message
            EntityResponse response = EntityResponse.builder()
                    .errors(Collections.singletonList(ex.getMessage()))
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    //    post endpoints to create an apartment
    @PostMapping("/apartment")
    public ResponseEntity<EntityResponse> createApartment(@RequestBody ApartmentRequest apartmentRequest) {
        try {
            Church church = churchService.getChurchByName(apartmentRequest.getChurchName());
            if (church == null) {
                throw new RuntimeException("Church not found with name: " + apartmentRequest.getChurchName());
            }

            Apartment apartment = new Apartment();
            apartment.setName(apartmentRequest.getName());
            apartment.setDescription(apartmentRequest.getDescription());
            apartment.setPrice(apartmentRequest.getPrice());
            apartment.setCapacity(apartmentRequest.getCapacity());
            apartment.setAmenities(apartmentRequest.getAmenities());
            apartment.setAddress(apartmentRequest.getAddress());
            apartment.setChurch(church);

            Apartment createdApartment = apartmentService.createApartment(apartment);
            String successMessage = "Apartment " + createdApartment.getName() + " created successfully";
            EntityResponse response = EntityResponse.builder()
                    .success(true)
                    .message(successMessage)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException ex) {
            // Handle the exception and return an appropriate response with the error message
            EntityResponse response = EntityResponse.builder()
                    .errors(Collections.singletonList(ex.getMessage()))
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}