package com.chablis.sanctified_journeys.user;

import com.chablis.sanctified_journeys.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    // Define a method to retrieve the roles of a user by their username
    // Use a native query to directly fetch the role as a string based on the username
    @Query(value = "SELECT roles FROM _user WHERE email = :email", nativeQuery = true)
    String findRoleByEmail(String email);
}
