package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundingBasket {
    // Private State
    @JsonProperty("Needs")
    private List<Need> needs;

    // Constructor
    public FundingBasket(){
        needs = new ArrayList<>();
    }

    // Methods
    public List<Need> getBasket(){
        return needs;
    }

    public boolean addToBasket(Need need){
        // Check is real need that needs funding
        if(need != null && need.getAllFunded() == false){
            return needs.add(need);
        }
        return false;
    }

    public boolean removeFromBasket(Need need){
        return needs.remove(need);     
    }
}
