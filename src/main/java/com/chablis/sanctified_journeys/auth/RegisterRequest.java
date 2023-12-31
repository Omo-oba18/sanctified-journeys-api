package com.chablis.sanctified_journeys.auth;

import com.chablis.sanctified_journeys.user.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    @Email(message = "Please provide a valid email address.")
    private String email;
    private String password;
    private Role role;

}
