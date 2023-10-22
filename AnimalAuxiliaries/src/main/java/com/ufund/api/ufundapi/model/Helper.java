package com.ufund.api.ufundapi.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Helper implements User {
    
    @JsonProperty("id")
    private String id;
    @JsonProperty("username")
    private String username;

    //funding basket 

    private static final String HELPER_ID_MODIFIER = "H"; 

    public Helper(String username) {
        this.username = username; 
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString(); 
    }

    public String getId() {
        return this.id; 
    }

    public String getUsername() {
        return this.username; 
    }
}
