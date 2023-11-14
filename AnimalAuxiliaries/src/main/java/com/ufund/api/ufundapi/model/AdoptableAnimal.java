package com.ufund.api.ufundapi.model;

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
    @JsonProperty("isAdopted")
    private boolean isAdopted; 
    //@JsonProperty("adoption_applications")
    //private Map<String, AdoptionApplication> adoption_applications;

    //Default values
    private static final String DEFAULT_NAME = "Animal";
    private static final String DEFAULT_DESCRIPTION = "";
    private static final String DEFAULT_SPECIES = "Dog";
    private static final String DEFAULT_IMAGE_URL = "";
    private static final boolean DEFAULT_IS_ADOPTED = false; 
    private static final boolean ADOPTED = true; 
    
    //private static final int INITIAL_MAP_SIZE = 45;

    //Constructors

    /**
     * Creates an adoptable animal with default values
     */
    public AdoptableAnimal(){
        this.id = UUID.randomUUID().toString();
        this.name = DEFAULT_NAME;
        this.description = DEFAULT_DESCRIPTION;
        this.species = DEFAULT_SPECIES;
        this.image_url = DEFAULT_IMAGE_URL;
        this.isAdopted = DEFAULT_IS_ADOPTED; 
        //this.adoption_applications = new HashMap<>(INITIAL_MAP_SIZE);
        
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
        this.isAdopted = DEFAULT_IS_ADOPTED; 
        //this.adoption_applications = new HashMap<>(INITIAL_MAP_SIZE);
    }

    //Methods

    public void adopt() {
        this.isAdopted = ADOPTED; 
    }
    
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

    /**
     * 
     * @return animal id
     */
    public String getId(){
        return this.id;
    }

    /**
     * 
     * @return animal name
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     * @return animal description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * 
     * @return animal species
     */
    public String getSpecies(){
        return this.species;
    }

    /**
     * 
     * @return animal image url
     */
    public String getImageUrl(){
        return this.image_url;
    }

    /**
     * retrieves if the animal has been adopted or not
     * @return
     */
    public boolean getIsAdopted() {
        return this.isAdopted; 
    }
    //Setters

    /**
     * sets animal id value
     * @param id new id value
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * sets animal name
     * @param name new name value
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * sets animal description
     * @param description new description value
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * sets animal species
     * @param species new species value
     */
    public void setSpecies(String species){
        this.species = species;
    }

    /**
     * set animal image_url
     * @param image_url new url value
     */
    public void setImageUrl(String image_url){
        this.image_url = image_url;
    }

}