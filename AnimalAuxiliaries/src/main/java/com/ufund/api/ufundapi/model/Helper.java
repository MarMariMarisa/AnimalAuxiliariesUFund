package com.ufund.api.ufundapi.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Helper implements User {
    
    @JsonProperty("id")
    private final String id;
    @JsonProperty("username")
    private final String username;

    @JsonProperty("basket")
    private List<Need> fundingBasket;

    private static final String HELPER_ID_MODIFIER = "H"; 

    public Helper(String username) {
        this.username = username;
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString(); 
        this.fundingBasket = new  ArrayList<>();
    }

    public Helper(){
        this.username = "";
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString();
        this.fundingBasket = new ArrayList<>(); 
    }

    public boolean addToFundingBasket(Need need){
        // Check is real need that needs funding
        if(need != null && need.getAllFunded() == false){
            return fundingBasket.add(need);
        }
        return false;
    }


    public boolean removeFromFundingBasket(Need need){
        return fundingBasket.remove(need);     
    }
    
    
    public Need[] getBasketNeeds() {
        //return (Need[]) fundingBasket.toArray();
        return fundingBasket.toArray(new Need[0]);
    }

    public List<Need> getFundingBasket() {
        return fundingBasket; 
    }

    public String getId() {
        return this.id; 
    }

    public String getUsername() {
        return this.username; 
    }
}
