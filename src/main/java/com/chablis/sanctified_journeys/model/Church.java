package com.chablis.sanctified_journeys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "church")
public class Church extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)

    @NotBlank(message = "You need to provide the Church Name")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "The Clergy Name is required")
    private String clergyName;

    @Column(nullable = false)
    private String phoneContact;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @OneToMany(mappedBy = "church", cascade = CascadeType.ALL)
    private List<Apartment> apartments;

    public Church(String churchName, Address address) {
        super();
    }
}
