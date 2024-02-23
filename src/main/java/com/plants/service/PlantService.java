package com.plants.service;

import com.plants.entities.Plant;
import com.plants.repositories.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {
    private final PlantRepository plantRepository;
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }
    public List<Plant> searchPlants(Boolean hasFruit, Integer quantity){
        if (hasFruit != null && quantity != null && hasFruit) {
            return this.plantRepository.findByHasFruitTrueAndQuantityLessThan(quantity);
        }else if (hasFruit != null && quantity != null && !hasFruit) {
            return this.plantRepository.findByHasFruitFalseAndQuantityLessThan(quantity);
        }else if (hasFruit != null && hasFruit) {
            return this.plantRepository.findByHasFruitTrue();
        }else if (hasFruit != null && !hasFruit) {
            return this.plantRepository.findByHasFruitFalse();
        }else if (quantity != null) {
            return this.plantRepository.findByQuantityLessThan(quantity);
        }else {
            return new ArrayList<Plant>();
        }
    }
    public Iterable<Plant> getAllPlants(){
        return this.plantRepository.findAll();
    }
    public Optional<Plant> getPlantById(Integer id){
        return this.plantRepository.findById(id);
    }
    public Plant createNewPlant(Plant plant){
        if (plant != null){
            return this.plantRepository.save(plant);
        }else{
            throw new NullPointerException("Error Plant not Found");
        }
    }
    public Plant updatePlant(Integer id, Plant plant){
        Optional<Plant> plantToUpdateOptional = this.plantRepository.findById(id);
        if (plantToUpdateOptional.isEmpty()) {
            return null;
        }
        Plant plantToUpdate = plantToUpdateOptional.get();
        if (plant.getHasFruit() != null) {
            plantToUpdate.setHasFruit(plant.getHasFruit());
        }
        if (plant.getQuantity() != null) {
            plantToUpdate.setQuantity(plant.getQuantity());
        }
        if (plant.getName() != null) {
            plantToUpdate.setName(plant.getName());
        }
        if (plant.getWateringFrequency() != null) {
            plantToUpdate.setWateringFrequency(plant.getWateringFrequency());
        }
        return this.plantRepository.save(plantToUpdate);
    }
    public Plant deletePlant(Integer id){
        Optional<Plant> plantToDeleteOptional = this.plantRepository.findById(id);
        if (plantToDeleteOptional.isEmpty()) {
            return null;
        }
        Plant plantToDelete = plantToDeleteOptional.get();
        this.plantRepository.delete(plantToDelete);
        return plantToDelete;
    }
}
