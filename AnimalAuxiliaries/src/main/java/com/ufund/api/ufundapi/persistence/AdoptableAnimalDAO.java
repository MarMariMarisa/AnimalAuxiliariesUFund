package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ufund.api.ufundapi.model.AdoptableAnimal;
import com.ufund.api.ufundapi.model.AdoptionCupboard;

@Component
public class AdoptableAnimalDAO {

    AdoptionCupboard cupboard; 
    private ObjectMapper objectMapper; 
    private String animalFileName; 

    /**
     * Creates a new Animal Data Access Object 
     * 
     * @param animalFileName    filename to read from and write to 
     * @param objectMapper      Provides JSON Object to/from Java Object serialization and deserialization 
     * 
     * @throws IOException when the file cannot be acccessed
     */
    public AdoptableAnimalDAO(@Value("${animal.file}") String animalFileName, ObjectMapper objectMapper) throws IOException {
        this.animalFileName = animalFileName; 
        this.objectMapper = objectMapper; 
        load();
    }

    private AdoptableAnimal[] getAnimalArray() {
        List<AdoptableAnimal> animalList = cupboard.getAnimals();
        return animalList.toArray(new AdoptableAnimal[0]); 
    }

    private boolean save() throws IOException {
        AdoptableAnimal[] animalArray = getAnimalArray(); 
        objectMapper.writeValue(new File(animalFileName), animalArray); 
        return true; 
    }

    private boolean load() throws IOException {
        cupboard = new AdoptionCupboard(); 

        AdoptableAnimal[] animals = objectMapper.readValue(new File(animalFileName), AdoptableAnimal[].class); 

        for(AdoptableAnimal animal : animals) { 
            cupboard.addAnimal(animal); 
        }

        return true; 
    }

    public AdoptableAnimal[] getAnimals() throws IOException {
        synchronized (cupboard.getAnimals()) {
            return getAnimalArray(); 
        }
    }

    public AdoptableAnimal getAnimal(String id) throws IOException {
        synchronized(cupboard) {
            return cupboard.getAdoptableAnimalOnId(id); 
        }
    }

    public AdoptableAnimal createAnimal(AdoptableAnimal animal) throws IOException {
        synchronized(cupboard) {
            if(cupboard.addAnimal(animal)) {
                save(); //may throw IOException
                return animal; 
            }
            return null; 
        }
    }

    public AdoptableAnimal updateAnimal(AdoptableAnimal animal) throws IOException{
        synchronized(cupboard) {
            if(cupboard.updateAnimal(animal)) {
                save(); 
                return animal; 
            }
            return null; 
        }
    }

    public AdoptableAnimal[] findAnimalOnSpecies(String containsText) throws IOException {
        synchronized(cupboard) {
            return cupboard.getAdoptableAnimalOnSpecies(containsText).toArray(new AdoptableAnimal[0]); 
        }
    }

    public boolean deleteAnimal(String id) throws IOException {
        synchronized(cupboard) {
            if(cupboard.deleteAnimal(id)) {
                save(); 
                return true; 
            }
            return false; 
        }
    }

    public boolean adoptAnimal(AdoptableAnimal animalToBeAdopted) throws IOException {
        synchronized(cupboard) {
            if(cupboard.adoptAnimal(animalToBeAdopted)) {
                save(); 
                return true; 
            }
            return false; 
        }
    }

    //public AdoptableAnimal[] findAdoptableAnimals(String containsText) throws IOException {

    
    
}
