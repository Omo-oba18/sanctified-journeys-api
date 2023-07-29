package com.chablis.sanctified_journeys.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityResponse {

    private List<String> errors;
    private Boolean success;
    private String message;

    public EntityResponse(List<String> errors, boolean success, String message) {
        this.errors = errors;
        this.success = success;
        this.message = message;
    }
}
