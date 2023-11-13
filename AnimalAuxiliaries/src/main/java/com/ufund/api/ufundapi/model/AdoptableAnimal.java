package com.ufund.api.ufundapi.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdoptableAnimal {

    //Private State
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("species")
    private String species;
    @JsonProperty("image")
    private String image_url;
    @JsonProperty("adoption_applications")
    private Map<String, AdoptionApplication> adoption_applications;

    //Default values
    private static final String DEFAULT_NAME = "Animal";
    private static final String DEFAULT_DESCRIPTION = "";
    private static final String DEFAULT_SPECIES = "Dog";
    private static final String DEFAULT_IMAGE_URL = "";

    private static final int INITIAL_MAP_SIZE = 45;

    /**
     * Creates an adoptable animal with default values
     */
    public AdoptableAnimal(){
        this.id = UUID.randomUUID().toString();
        this.name = DEFAULT_NAME;
        this.description = DEFAULT_DESCRIPTION;
        this.species = DEFAULT_SPECIES;
        this.image_url = DEFAULT_IMAGE_URL;
        
    }

    /**
     * Creates an adoptable animal with the given values
     * @param name
     * @param description
     * @param species
     */
    public AdoptableAnimal(@JsonProperty("name") String name, @JsonProperty("description") String description, 
            @JsonProperty("species") String species){

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.species = species;
        this.adoption_applications = new HashMap<>(INITIAL_MAP_SIZE);
    }

    //Methods
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdoptableAnimal other = (AdoptableAnimal) obj;
        return id.equals(other.id);
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getSpecies(){
        return this.species;
    }

    //Setters
    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setSpecies(String species){
        this.species = species;
    }

}