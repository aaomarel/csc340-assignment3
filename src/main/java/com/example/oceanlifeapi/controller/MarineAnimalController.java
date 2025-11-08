package com.example.oceanlifeapi.controller;

import com.example.oceanlifeapi.model.MarineAnimal;
import com.example.oceanlifeapi.service.MarineAnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * MVC Controller for Marine Animal application.
 * Handles HTTP requests and returns views with model data.
 */
@Controller
@RequestMapping("/animals")
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
     * GET /animals
     * Get all marine animals and display them in a list view.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping
    public String getAllMarineAnimals(Model model) {
        List<MarineAnimal> animalList = marineAnimalService.getAllMarineAnimals();
        model.addAttribute("animalList", animalList);
        return "animal-list";
    }

    /**
     * GET /animals/{id}
     * Get a marine animal by ID and display details.
     *
     * @param id    the ID of the animal
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/{id}")
    public String getMarineAnimalById(@PathVariable Long id, Model model) {
        Optional<MarineAnimal> animal = marineAnimalService.getMarineAnimalById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "animal-details";
        } else {
            return "redirect:/animals";
        }
    }

    /**
     * GET /animals/new
     * Display the form to create a new marine animal.
     *
     * @return the name of the create form view
     */
    @GetMapping("/new")
    public String showCreateForm() {
        return "animal-create";
    }

    /**
     * POST /animals/new
     * Add a new marine animal.
     *
     * @param marineAnimal the animal to add (populated from form)
     * @return redirect to the animals list
     */
    @PostMapping("/new")
    public String addMarineAnimal(MarineAnimal marineAnimal) {
        MarineAnimal savedAnimal = marineAnimalService.addMarineAnimal(marineAnimal);
        return "redirect:/animals/" + savedAnimal.getAnimalId();
    }

    /**
     * GET /animals/update/{id}
     * Display the form to update an existing marine animal.
     *
     * @param id    the ID of the animal to update
     * @param model the model to add attributes to
     * @return the name of the update form view
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<MarineAnimal> animal = marineAnimalService.getMarineAnimalById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "animal-update";
        } else {
            return "redirect:/animals";
        }
    }

    /**
     * POST /animals/update
     * Update an existing marine animal.
     *
     * @param marineAnimal the animal with updated details (populated from form)
     * @return redirect to the animal details page
     */
    @PostMapping("/update")
    public String updateMarineAnimal(MarineAnimal marineAnimal) {
        marineAnimalService.updateMarineAnimal(marineAnimal.getAnimalId(), marineAnimal);
        return "redirect:/animals/" + marineAnimal.getAnimalId();
    }

    /**
     * GET /animals/delete/{id}
     * Delete a marine animal by ID.
     *
     * @param id the ID of the animal to delete
     * @return redirect to the animals list
     */
    @GetMapping("/delete/{id}")
    public String deleteMarineAnimal(@PathVariable Long id) {
        marineAnimalService.deleteMarineAnimal(id);
        return "redirect:/animals";
    }

    /**
     * GET /animals/species/{species}
     * Get all marine animals by species.
     *
     * @param species the species to search for
     * @param model   the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/species/{species}")
    public String getMarineAnimalsBySpecies(@PathVariable String species, Model model) {
        List<MarineAnimal> animalList = marineAnimalService.getMarineAnimalsBySpecies(species);
        model.addAttribute("animalList", animalList);
        return "animal-list";
    }

    /**
     * GET /animals/search?name=substring
     * Search for marine animals by name.
     *
     * @param name  the string to search for in animal names
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/search")
    public String searchMarineAnimalsByName(@RequestParam String name, Model model) {
        List<MarineAnimal> animalList = marineAnimalService.getMarineAnimalsByNameContaining(name);
        model.addAttribute("animalList", animalList);
        model.addAttribute("searchTerm", name);
        return "animal-list";
    }
}
