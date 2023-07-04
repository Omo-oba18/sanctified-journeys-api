package com.chablis.sanctified_journeys.repository;

import com.chablis.sanctified_journeys.model.Church;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChurchRepository extends JpaRepository<Church, Integer> {
}
