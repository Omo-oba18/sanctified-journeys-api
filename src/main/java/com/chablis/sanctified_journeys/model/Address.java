package com.chablis.sanctified_journeys.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int addressId;

//    @NotBlank(message="Address1 must not be blank")
//    @Size(min=5, message="Address1 must be at least 5 characters long")
    private String address1;

    private String address2;

//    @NotBlank(message="City must not be blank")
//    @Size(min=5, message="City must be at least 5 characters long")
    private String city;

//    @NotBlank(message="State must not be blank")
//    @Size(min=5, message="State must be at least 5 characters long")
    private String state;
}
