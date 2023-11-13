package com.ufund.api.ufundapi.model;

import java.util.HashMap;
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
    

}
