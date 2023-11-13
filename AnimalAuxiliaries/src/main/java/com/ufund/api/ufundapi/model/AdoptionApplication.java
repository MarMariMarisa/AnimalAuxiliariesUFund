package com.ufund.api.ufundapi.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdoptionApplication {
    
    //Private State
    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("helper_id")
    private String helper_id;
    @JsonProperty("animal_id")
    private String animal_id;
    @JsonProperty("helper_username")
    private String helper_username;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("information")
    private String information;

    //Default Values
    private static final String DEFAULT_STATUS = "pending";
    private static final String DEFAULT_HELPER_ID = "";
    private static final String DEFAULT_ANIMAL_ID = "";
    private static final String DEFAULT_HELPER_USERNAME = "username";
    private static final String DEFAULT_CONTACT = "contact";
    private static final String DEFAULT_INFORMATION = "information";

    /**
     * Creates an Adoption Application with default values
     * 
     */
    public AdoptionApplication(){
        this.id = UUID.randomUUID().toString();
        this.status = DEFAULT_STATUS;
        this.helper_id = DEFAULT_HELPER_ID;
        this.animal_id = DEFAULT_ANIMAL_ID;
        this.helper_username = DEFAULT_HELPER_USERNAME;
        this.contact = DEFAULT_CONTACT;
        this.information = DEFAULT_INFORMATION;

    }

    public AdoptionApplication(@JsonProperty("status") String status, @JsonProperty("helper_id") String helper_id, 
            @JsonProperty("animal_id") String animal_id, @JsonProperty("helper_username") String helper_username, 
            @JsonProperty("contact") String contact, @JsonProperty("information") String information) {
        
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.helper_id = helper_id;
        this.animal_id = animal_id;
        this.helper_username = helper_username;
        this.contact = contact;
        this.information = information;
    }

    public boolean setStatus(String status){
        if(status != null){
            this.status = status;
            return true;
        }
        return false;
    }

    public void setHelperUsername(String helper_username){
        this.helper_username = helper_username;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    public void setInformation(String information){
        this.information = information;
    }

    public String getId(){
        return this.id;
    }

    public String getStatus(){
        return this.status;
    }

    public String getHelperId(){
        return this.helper_id;
    }

    public String getAnimalId(){
        return this.animal_id;
    }

    public String getHelperUsername(){
        return this.helper_username;
    }

    public String getContact(){
        return this.contact;
    }

    public String getInformation(){
        return this.information;
    }
}
