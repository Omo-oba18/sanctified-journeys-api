package com.chablis.sanctified_journeys.service;

import com.chablis.sanctified_journeys.model.Address;
import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.model.Church;
import com.chablis.sanctified_journeys.repository.ApartmentRepository;
import com.chablis.sanctified_journeys.utils.ErrorUtils;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ChurchService churchService;

    //    Search apartment service through price, location and church
    public List<Apartment> searchApartmentsByLocation(String state, String city) {
        return apartmentRepository.findApartmentsByLocation(state, city);
    }

    public List<Apartment> searchApartmentsByLocationAndPriceAndCapacity(String state, String city, double minPrice, double maxPrice, int minCapacity) {
        return apartmentRepository.findApartmentsByLocationAndPriceAndCapacity(state, city, minPrice, maxPrice, minCapacity);
    }

    public List<Apartment> searchApartmentsByLocationAndAmenity(String state, String city, String amenity) {
        return apartmentRepository.findApartmentsByLocationAndAmenity(state, city, amenity);
    }

    public List<Apartment> findApartmentsNearChurch(String churchState, String churchCity) {
        return apartmentRepository.findApartmentsNearChurch(churchState, churchCity);
    }

    //    create apartment service
    public Apartment createApartment(Apartment apartment) {
        try {
            Validation.buildDefaultValidatorFactory().getValidator().validate(apartment);
            return apartmentRepository.save(apartment);
        } catch (ConstraintViolationException ex) {
            ErrorUtils.handleConstraintViolationException(ex);
            return null; // We should return something here, even though it won't be reached due to the throw statement above.
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Error: Unable to save Apartment. Please check the provided data.");
        }
    }

}