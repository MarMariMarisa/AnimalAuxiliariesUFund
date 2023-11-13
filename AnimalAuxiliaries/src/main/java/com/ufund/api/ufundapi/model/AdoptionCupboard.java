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
    @JsonProperty("animals")
    private Map<String, AdoptableAnimal> animals;

    private static final int INITIAL_MAP_SIZE = 45;

    //Constructors

    /**
     * Creates instance of AdoptionCupboard with an initial map size 45
     */
    public AdoptionCupboard(){
        animals = new HashMap<>(INITIAL_MAP_SIZE);
    }

    //Methods

    /**
     * finds the given animal in the animal array and adopts them
     * @param animal to be moved from currentAnimals into adoptedAnimals
     * @return true if animal moved, false if it wasn't
     */
    public boolean adoptAnimal(AdoptableAnimal animal){
        if(animal.getId() != null && (this.animals.containsKey(animal.getId()))){
            this.animals.get(animal.getId()).adopt();
            return true;
        }
        return false;
    }

    /**
     * 
     * @return List of AdoptableAnimals in currentAnimals
     */
    public List<AdoptableAnimal> getAnimals(){
        return new ArrayList<AdoptableAnimal>(animals.values());
    }

    /**
     * Searches list of current animals to find animals that contain
     * the species value passed
     * 
     * @param speciesSubstring What the function is searching for
     * @return List of AdoptableAnimals that match the search querey 
     */
    public List<AdoptableAnimal> getAdoptableAnimalOnSpecies(String speciesSubstring){
        List<AdoptableAnimal> matchingAnimals = new ArrayList<>();
        if (speciesSubstring != null){
            for (AdoptableAnimal animal : animals.values()){
                if(animal.getSpecies().toLowerCase().contains(speciesSubstring.toLowerCase())){
                    matchingAnimals.add(animal);
                }
            }
        }
        return matchingAnimals;
    }

    /**
     * Fetches the animal matching the id passed
     * 
     * @param id What the function is searching for
     * @return the AdoptableAnimal with the matching Id
     */
    public AdoptableAnimal getAdoptableAnimalOnId(String id){
        if(id != null){
            return animals.get(id);
        }
        return null;
    }

    /**
     * Adds an animal to currentAnimals
     * 
     * @param newAnimal what is to be added to currentAnimals
     * @return true if animal is added, false if it failed
     */
    public boolean addAnimal(AdoptableAnimal newAnimal){
        if(newAnimal != null && !(this.animals.containsKey(newAnimal.getId()))){
            this.animals.put(newAnimal.getId(), newAnimal);
            return true;
        }
        return false;
    }

    /**
     * Updates the animal with the same id as the one passed
     * 
     * @param animal animal with updated values
     * @return true is animal is updated, false if it fails
     */
    public boolean updateAnimal(AdoptableAnimal animal){
        if(animals.containsKey(animal.getId())){
            animals.put(animal.getId(), animal);
            return true;
        }
        return false;
    }

   /**
    * Permantantly removes an animal from currentAnimals
    *
    * @param animalId id of the animal to be deleted
    * @return true if the animal was deleated, false if it wasn't
    */
    public boolean deleteAnimal(String animalId){
        if(animalId != null){
            if(animals.remove(animalId) != null) {
                return true;
            }
        }
        return false;
    }
    

}
