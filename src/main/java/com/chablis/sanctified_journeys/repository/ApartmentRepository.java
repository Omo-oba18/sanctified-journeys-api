package com.chablis.sanctified_journeys.repository;

import com.chablis.sanctified_journeys.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    List<Apartment> findByAddressContainingAndPriceLessThanEqualAndChurchesNameContaining(String address, float price, String churchName);

    List<Apartment> findByAddressContainingAndPriceLessThanEqual(String address, float price);

    List<Apartment> findByAddressContainingAndChurchesNameContaining(String address, String churchName);

    List<Apartment> findByPriceLessThanEqualAndChurchesNameContaining(float price, String churchName);

    List<Apartment> findByAddressContaining(String address);

    List<Apartment> findByPriceLessThanEqual(float price);

    List<Apartment> findByChurchesNameContaining(String churchName);

}
