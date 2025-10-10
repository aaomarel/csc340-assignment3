package com.example.oceanlifeapi.service;

import com.example.oceanlifeapi.model.MarineAnimal;
import com.example.oceanlifeapi.repository.MarineAnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for MarineAnimal business logic.
 * Acts as an intermediary between the controller and repository.
 */
@Service
public class MarineAnimalService {

    private final MarineAnimalRepository marineAnimalRepository;

    /**
     * Constructor injection of MarineAnimalRepository.
     *
     * @param marineAnimalRepository the repository for data access
     */
    public MarineAnimalService(MarineAnimalRepository marineAnimalRepository) {
        this.marineAnimalRepository = marineAnimalRepository;
    }

    /**
     * Get all marine animals.
     *
     * @return list of all marine animals
     */
    public List<MarineAnimal> getAllMarineAnimals() {
        return marineAnimalRepository.findAll();
    }

    /**
     * Get a marine animal by its ID.
     *
     * @param id the ID of the animal
     * @return Optional containing the animal if found
     */
    public Optional<MarineAnimal> getMarineAnimalById(Long id) {
        return marineAnimalRepository.findById(id);
    }

    /**
     * Add a new marine animal.
     *
     * @param marineAnimal the animal to add
     * @return the saved animal
     */
    public MarineAnimal addMarineAnimal(MarineAnimal marineAnimal) {
        return marineAnimalRepository.save(marineAnimal);
    }

    /**
     * Update an existing marine animal.
     *
     * @param id            the ID of the animal to update
     * @param animalDetails the new details for the animal
     * @return the updated animal
     * @throws RuntimeException if animal not found
     */
    public MarineAnimal updateMarineAnimal(Long id, MarineAnimal animalDetails) {
        MarineAnimal animal = marineAnimalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marine Animal not found with id: " + id));

        animal.setName(animalDetails.getName());
        animal.setDescription(animalDetails.getDescription());
        animal.setSpecies(animalDetails.getSpecies());
        animal.setHabitat(animalDetails.getHabitat());

        return marineAnimalRepository.save(animal);
    }

    /**
     * Delete a marine animal by ID.
     *
     * @param id the ID of the animal to delete
     */
    public void deleteMarineAnimal(Long id) {
        marineAnimalRepository.deleteById(id);
    }

    /**
     * Get all marine animals by species.
     *
     * @param species the species to search for
     * @return list of animals matching the species
     */
    public List<MarineAnimal> getMarineAnimalsBySpecies(String species) {
        return marineAnimalRepository.findBySpecies(species);
    }

    /**
     * Get all marine animals whose name contains the given string.
     *
     * @param name the string to search for in names
     * @return list of animals with matching names
     */
    public List<MarineAnimal> getMarineAnimalsByNameContaining(String name) {
        return marineAnimalRepository.findByNameContainingIgnoreCase(name);
    }
}
