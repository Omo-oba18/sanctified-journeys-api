package com.chablis.sanctified_journeys.service;

import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.repository.ApartmentRepository;
import com.chablis.sanctified_journeys.request.ApartmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

//    Search apartment service through price, location and church
    public List<Apartment> searchApartments(String address, float price, String nearestChurch) {
        if (address != null && !address.isEmpty() && price > 0 && nearestChurch != null && !nearestChurch.isEmpty()) {
            return apartmentRepository.findByAddressContainingAndPriceLessThanEqualAndChurchesNameContaining(address, price, nearestChurch);
        } else if (address != null && !address.isEmpty() && price > 0) {
            return apartmentRepository.findByAddressContainingAndPriceLessThanEqual(address, price);
        } else if (address != null && !address.isEmpty() && nearestChurch != null && !nearestChurch.isEmpty()) {
            return apartmentRepository.findByAddressContainingAndChurchesNameContaining(address, nearestChurch);
        } else if (price > 0 && nearestChurch != null && !nearestChurch.isEmpty()) {
            return apartmentRepository.findByPriceLessThanEqualAndChurchesNameContaining(price, nearestChurch);
        } else if (address != null && !address.isEmpty()) {
            return apartmentRepository.findByAddressContaining(address);
        } else if (price > 0) {
            return apartmentRepository.findByPriceLessThanEqual(price);
        } else if (nearestChurch != null && !nearestChurch.isEmpty()) {
            return apartmentRepository.findByChurchesNameContaining(nearestChurch);
        }

        return Collections.emptyList();
    }

    //    create apartment service
    public Apartment createApartment(ApartmentRequest request) {
        Apartment apartment = new Apartment();
        apartment.setAvailability(request.isAvailability());
        apartment.setPrice(request.getPrice());
        apartment.setAddress(request.getAddress());
        // Perform any additional processing or validation if needed
        return apartmentRepository.save(apartment);
    }
}
