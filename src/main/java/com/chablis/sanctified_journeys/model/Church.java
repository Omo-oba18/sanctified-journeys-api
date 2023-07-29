package com.chablis.sanctified_journeys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Objects;

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
    @JsonIgnore // Add this annotation to break the circular reference
    private List<Apartment> apartments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Church church)) return false;
        return Objects.equals(id, church.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Church(String churchName, Address address) {
        super();
    }
}
