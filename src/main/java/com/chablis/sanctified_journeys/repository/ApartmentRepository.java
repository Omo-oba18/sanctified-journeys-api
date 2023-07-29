package com.chablis.sanctified_journeys.repository;

import com.chablis.sanctified_journeys.model.Address;
import com.chablis.sanctified_journeys.model.Apartment;
import com.chablis.sanctified_journeys.model.Church;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query("SELECT a FROM Apartment a WHERE a.address.state = :state AND a.address.city = :city")
    List<Apartment> findApartmentsByLocation(String state, String city);

    @Query("SELECT a FROM Apartment a WHERE a.address.state = :state AND a.address.city = :city AND a.price >= :minPrice AND a.price <= :maxPrice AND a.capacity >= :minCapacity")
    List<Apartment> findApartmentsByLocationAndPriceAndCapacity(String state, String city, double minPrice, double maxPrice, int minCapacity);

    @Query("SELECT a FROM Apartment a WHERE a.address.state = :state AND a.address.city = :city AND :amenity MEMBER OF a.amenities")
    List<Apartment> findApartmentsByLocationAndAmenity(String state, String city, String amenity);

    // Custom method for finding apartments near a specific church location
    @Query("SELECT a FROM Apartment a WHERE a.address.state = :churchState AND a.address.city = :churchCity")
    List<Apartment> findApartmentsNearChurch(String churchState, String churchCity);

}
