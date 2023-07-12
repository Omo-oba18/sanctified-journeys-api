package com.chablis.sanctified_journeys.controller;

import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.repository.ApartmentRepository;
import com.chablis.sanctified_journeys.request.ApartmentRequest;
import com.chablis.sanctified_journeys.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Apartment>> searchApartments(
            @RequestParam(required = false) String address,
            @RequestParam(required = false, defaultValue = "0") float price,
            @RequestParam(required = false) String nearestChurch) {

        List<Apartment> apartments = apartmentService.searchApartments(address, price, nearestChurch);
        return ResponseEntity.ok(apartments);
    }

}
