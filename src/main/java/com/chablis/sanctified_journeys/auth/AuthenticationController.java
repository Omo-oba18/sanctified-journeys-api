package com.chablis.sanctified_journeys.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final AuthenticationRequestValidator authenticationRequestValidator;

    @InitBinder("authenticationRequest")
    protected void initAuthenticationRequestBinder(WebDataBinder binder) {
        binder.addValidators(authenticationRequestValidator);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new AuthenticationResponse(errors, false));
        }
        try{
            AuthenticationResponse response = service.register(request);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }catch(IllegalArgumentException e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return ResponseEntity.badRequest().body(new AuthenticationResponse(errors, false));
        }
    }

@PostMapping("/login")
public ResponseEntity<AuthenticationResponse> login(
        @Valid @RequestBody AuthenticationRequest request,
        BindingResult bindingResult
) {
    if (bindingResult.hasErrors()) {
        List<String> errors = bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new AuthenticationResponse(errors, false));
    }

    try {
        AuthenticationResponse response = service.authenticate(request);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    } catch (IllegalArgumentException e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return ResponseEntity.badRequest().body(new AuthenticationResponse(errors, false));
    }
}
}

