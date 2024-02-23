package com.plants.controllers;

import java.lang.Iterable;
import java.util.List;
import java.util.Optional;

import com.plants.entities.Plant;
import com.plants.service.PlantService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PlantController {

    private final PlantService plantService;

    public PlantController(final PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/plants/search")
    public List<Plant> searchPlants(
            @RequestParam(name="hasFruit", required = false) Boolean hasFruit,
            @RequestParam(name="maxQuantity", required = false) Integer quantity
    ) {
        return this.plantService.searchPlants(hasFruit, quantity);
    }

    @GetMapping("/plants")
    public Iterable<Plant> getAllPlants() {
        return this.plantService.getAllPlants();
    }

    @GetMapping("/plants/{id}")
    public Optional<Plant> getPlantById(@PathVariable("id") Integer id) {
        return this.plantService.getPlantById(id);
    }

    @PostMapping("/plants")
    public Plant createNewPlant(@RequestBody Plant plant) {
        return this.plantService.createNewPlant(plant);
    }

    @PutMapping("/plants/{id}")
    public Plant updatePlant(@PathVariable("id") Integer id, @RequestBody Plant plant) {
        return plantService.updatePlant(id, plant);
    }

    @DeleteMapping("/plants/{id}")
    public Plant deletePlant(@PathVariable("id") Integer id) {
        return this.plantService.deletePlant(id);
    }

}