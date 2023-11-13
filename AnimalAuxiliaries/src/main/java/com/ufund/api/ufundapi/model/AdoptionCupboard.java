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

    //Constructors

    /**
     * Creates instance of AdoptionCupboard with an initial map size 45
     */
    public AdoptionCupboard(){
        currentAnimals = new HashMap<>(INITIAL_MAP_SIZE);
        adoptedAnimals = new HashMap<>(INITIAL_MAP_SIZE);
    }

    //Methods

    /**
     * Moves animal passed from currentAnimals into adoptedAnimals
     * @param animal to be moved from currentAnimals into adoptedAnimals
     * @return true if animal moved, false if it wasn't
     */
    public boolean adoptAnimal(AdoptableAnimal animal){
        if(animal.getId() != null && (this.currentAnimals.containsKey(animal.getId()))){
            this.adoptedAnimals.put(animal.getId(), animal);
            this.currentAnimals.remove(animal.getId(), animal);
            return true;
        }
        return false;
    }

    /**
     * 
     * @return List of AdoptableAnimals in currentAnimals
     */
    public List<AdoptableAnimal> getCurrentAnimals(){
        return new ArrayList<AdoptableAnimal>(currentAnimals.values());
    }

   /**
    * 
    * @return List of AdoptableAnimals from adoptedAnimals
    */
    public List<AdoptableAnimal> getAdoptedAnimals(){
        return new ArrayList<AdoptableAnimal>(adoptedAnimals.values());
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
            for (AdoptableAnimal animal : currentAnimals.values()){
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
            return currentAnimals.get(id);
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
        if(newAnimal != null && !(this.currentAnimals.containsKey(newAnimal.getId()))){
            this.currentAnimals.put(newAnimal.getId(), newAnimal);
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
        if(currentAnimals.containsKey(animal.getId())){
            currentAnimals.put(animal.getId(), animal);
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
            if(currentAnimals.remove(animalId) != null) {
                return true;
            }
        }
        return false;
    }
    

}
