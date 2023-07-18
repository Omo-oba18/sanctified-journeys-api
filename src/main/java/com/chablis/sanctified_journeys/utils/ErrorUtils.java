package com.chablis.sanctified_journeys.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class ErrorUtils {

    public static void handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String errorMessage = violations.stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Unknown validation error");
        throw new RuntimeException("Error: " + errorMessage);
    }
}
