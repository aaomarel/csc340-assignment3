package com.example.oceanlifeapi.controller;

import com.example.oceanlifeapi.model.MarineAnimal;
import com.example.oceanlifeapi.service.MarineAnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Marine Animal API.
 * Handles HTTP requests for CRUD operations on marine animals.
 */
@RestController
@RequestMapping("/api/marineanimals")
public class MarineAnimalController {

    private final MarineAnimalService marineAnimalService;

    /**
     * Constructor injection of MarineAnimalService.
     *
     * @param marineAnimalService the service for business logic
     */
    public MarineAnimalController(MarineAnimalService marineAnimalService) {
        this.marineAnimalService = marineAnimalService;
    }

    /**
     * GET /api/marineanimals
     * Get all marine animals.
     *
     * @return list of all marine animals
     */
    @GetMapping
    public List<MarineAnimal> getAllMarineAnimals() {
        return marineAnimalService.getAllMarineAnimals();
    }

    /**
     * GET /api/marineanimals/{id}
     * Get a marine animal by ID.
     *
     * @param id the ID of the animal
     * @return ResponseEntity with the animal or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MarineAnimal> getMarineAnimalById(@PathVariable Long id) {
        Optional<MarineAnimal> animal = marineAnimalService.getMarineAnimalById(id);
        return animal.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/marineanimals
     * Add a new marine animal.
     *
     * @param marineAnimal the animal to add
     * @return the created animal with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<MarineAnimal> addMarineAnimal(@RequestBody MarineAnimal marineAnimal) {
        MarineAnimal savedAnimal = marineAnimalService.addMarineAnimal(marineAnimal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnimal);
    }

    /**
     * PUT /api/marineanimals/{id}
     * Update an existing marine animal.
     *
     * @param id            the ID of the animal to update
     * @param animalDetails the new details for the animal
     * @return the updated animal
     */
    @PutMapping("/{id}")
    public ResponseEntity<MarineAnimal> updateMarineAnimal(
            @PathVariable Long id,
            @RequestBody MarineAnimal animalDetails) {
        try {
            MarineAnimal updatedAnimal = marineAnimalService.updateMarineAnimal(id, animalDetails);
            return ResponseEntity.ok(updatedAnimal);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/marineanimals/{id}
     * Delete a marine animal by ID.
     *
     * @param id the ID of the animal to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarineAnimal(@PathVariable Long id) {
        marineAnimalService.deleteMarineAnimal(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/marineanimals/species/{species}
     * Get all marine animals by species.
     *
     * @param species the species to search for
     * @return list of animals matching the species
     */
    @GetMapping("/species/{species}")
    public List<MarineAnimal> getMarineAnimalsBySpecies(@PathVariable String species) {
        return marineAnimalService.getMarineAnimalsBySpecies(species);
    }

    /**
     * GET /api/marineanimals/search?name=substring
     * Search for marine animals by name.
     *
     * @param name the string to search for in animal names
     * @return list of animals with names containing the search string
     */
    @GetMapping("/search")
    public List<MarineAnimal> searchMarineAnimalsByName(@RequestParam String name) {
        return marineAnimalService.getMarineAnimalsByNameContaining(name);
    }
}
