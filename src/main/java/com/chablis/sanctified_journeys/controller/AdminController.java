package com.chablis.sanctified_journeys.controller;

import com.chablis.sanctified_journeys.config.JWTService;
import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.model.Church;
import com.chablis.sanctified_journeys.request.ApartmentRequest;
import com.chablis.sanctified_journeys.service.ApartmentService;
import com.chablis.sanctified_journeys.service.ChurchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ChurchService churchService;
    private final ApartmentService apartmentService;
    private final JWTService jwtService;

    @PostMapping("/church")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Church> createChurch(@RequestBody Church church) {
        String jwtToken = jwtService.getCurrentJwtToken();

        if (jwtToken == null || !jwtService.isAdminUser(jwtToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            Church createdChurch = churchService.createChurch(church);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChurch);
        }

    }

    //    post endpoints to create an apartment
    @PostMapping("/apartment")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Apartment> createApartment(@RequestBody ApartmentRequest request) {
        String jwtToken = jwtService.getCurrentJwtToken();

        if (jwtToken == null || !jwtService.isAdminUser(jwtToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        else {
            Apartment apartment = apartmentService.createApartment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(apartment);
        }
    }
}