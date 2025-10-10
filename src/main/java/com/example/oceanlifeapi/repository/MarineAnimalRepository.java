package com.example.oceanlifeapi.repository;

import com.example.oceanlifeapi.model.MarineAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for MarineAnimal entity.
 * Spring Data JPA automatically implements this interface.
 */
@Repository
public interface MarineAnimalRepository extends JpaRepository<MarineAnimal, Long> {

    /**
     * Find all marine animals by species.
     *
     * @param species the species to search for
     * @return list of marine animals matching the species
     */
    List<MarineAnimal> findBySpecies(String species);

    /**
     * Find all marine animals whose name contains the given string (case-insensitive).
     *
     * @param name the string to search for in animal names
     * @return list of marine animals with names containing the search string
     */
    List<MarineAnimal> findByNameContainingIgnoreCase(String name);
}
