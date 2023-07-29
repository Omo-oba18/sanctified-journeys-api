package com.chablis.sanctified_journeys.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private List<String> errors;
    private String token;
    private Boolean success;

    public AuthenticationResponse(List<String> errors, boolean success) {
        this.errors = errors;
        this.success = success;
    }
}
