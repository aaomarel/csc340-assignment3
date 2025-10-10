package com.example.oceanlifeapi.model;

import jakarta.persistence.*;

/**
 * Entity class representing a Marine Animal in the database.
 * Maps to the marine_animal table in PostgreSQL.
 */
@Entity
@Table(name = "marine_animal")
public class MarineAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String species;

    private String habitat;

    /**
     * No-argument constructor required by JPA.
     */
    public MarineAnimal() {
    }

    /**
     * Constructor with all fields except animalId (auto-generated).
     *
     * @param name        the common name of the marine animal
     * @param description a brief description of the animal
     * @param species     the scientific name or species
     * @param habitat     the habitat where the animal lives
     */
    public MarineAnimal(String name, String description, String species, String habitat) {
        this.name = name;
        this.description = description;
        this.species = species;
        this.habitat = habitat;
    }

    // Getters and Setters

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
