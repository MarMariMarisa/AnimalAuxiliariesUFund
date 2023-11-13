package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class AdoptionCupboard {

    //Private State
    @JsonProperty("currentAnimals")
    private Map<String, AdoptableAnimal> currentAnimals;
    @JsonProperty("adoptedAnimals")
    private Map<String, AdoptableAnimal> adoptedAnimals;

    private static final int INITIAL_MAP_SIZE = 45;

    //Constructor
    public AdoptionCupboard(){
        currentAnimals = new HashMap<>(INITIAL_MAP_SIZE);
        adoptedAnimals = new HashMap<>(INITIAL_MAP_SIZE);
    }

    //Adopt Animal
    public boolean adoptAnimal(AdoptableAnimal animal){
        if(animal.getId() != null && (this.currentAnimals.containsKey(animal.getId()))){
            this.adoptedAnimals.put(animal.getId(), animal);
            return true;
        }
        return false;
    }

    //Get all current animals
    public List<AdoptableAnimal> getCurrentAnimals(){
        return new ArrayList<AdoptableAnimal>(currentAnimals.values());
    }

    //Get all adopted animals
    public List<AdoptableAnimal> getAdoptedAnimals(){
        return new ArrayList<AdoptableAnimal>(adoptedAnimals.values());
    }

    //Get Animals by Species
    public List<AdoptableAnimal> getAdoptableAnimalOnSpecies(String speciesSubstring){
        List<AdoptableAnimal> matchingAnimals = new ArrayList<>();
        if (speciesSubstring != null){
            for (AdoptableAnimal animal : currentAnimals.values()){
                if(animal.getSpecies().toLowerCase().contains(speciesSubstring.toLowerCase())){
                    matchingAnimals.add(animal);
                }
            }
        }
        return matchingAnimals;
    }

    //Get Animal by Id
    public AdoptableAnimal getAdoptableAnimalOnId(String id){
        if(id != null){
            return currentAnimals.get(id);
        }
        return null;
    }

    //Add Animal
    public boolean addAnimal(AdoptableAnimal newAnimal){
        if(newAnimal != null && !(this.currentAnimals.containsKey(newAnimal.getId()))){
            this.currentAnimals.put(newAnimal.getId(), newAnimal);
            return true;
        }
        return false;
    }

    //Update Animal
    public boolean updateAnimal(AdoptableAnimal animal){
        if(currentAnimals.containsKey(animal.getId())){
            currentAnimals.put(animal.getId(), animal);
            return true;
        }
        return false;
    }

    //Delete Animal
    public boolean deleteAnimal(String animalId){
        if(animalId != null){
            if(currentAnimals.remove(animalId) != null) {
                return true;
            }
        }
        return false;
    }
    

}
