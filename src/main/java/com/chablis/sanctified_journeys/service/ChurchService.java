package com.chablis.sanctified_journeys.service;

import com.chablis.sanctified_journeys.model.Church;
import com.chablis.sanctified_journeys.repository.ChurchRepository;
import com.chablis.sanctified_journeys.utils.ErrorUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChurchService {
    private final ChurchRepository churchRepository;

    public Church createChurch(Church church) {
        try {
            Validation.buildDefaultValidatorFactory().getValidator().validate(church);
            return churchRepository.save(church);
        } catch (ConstraintViolationException ex) {
            ErrorUtils.handleConstraintViolationException(ex);
            return null; // We should return something here, even though it won't be reached due to the throw statement above.
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Error: Unable to save church. Please check the provided data.");
        }
    }


    public Church getChurchById(Long church_id) {
        Optional<Church> optionalChurch = churchRepository.findById(church_id);
        return optionalChurch.orElse(null); // Return the church if found, or null if not found
    }
    public Church getChurchByName(String churchName) {
        return churchRepository.findByName(churchName);
    }

}
