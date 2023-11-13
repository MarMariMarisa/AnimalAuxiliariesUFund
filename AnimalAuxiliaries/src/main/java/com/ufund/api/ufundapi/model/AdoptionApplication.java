package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdoptionApplication {
    
    //Private State
    @JsonProperty("status")
    private String status;
    @JsonProperty("helper_id")
    private String helper_id;
    @JsonProperty("animal_id")
    private String animal_id;

    private static final String DEFAULT_STATUS = "pending";
    
    public AdoptionApplication(String helper_id, String animal_id){
        this.helper_id = helper_id;
        this.animal_id = animal_id;
        this.status = DEFAULT_STATUS;
    }

    public String getHelperId(){
        return this.helper_id;
    }

    public String getAnimalId(){
        return this.animal_id;
    }

}
