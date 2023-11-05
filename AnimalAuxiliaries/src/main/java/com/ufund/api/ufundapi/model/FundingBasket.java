package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class FundingBasket {
   // Private State
    @JsonProperty("needs")
    private List<Need> needs;

    // Constructor
    public FundingBasket(){
        needs = new ArrayList<>();
    }

    // Methods
    // public List<Need> getBasket(){
    //     return needs;
    // }

    public Need[] getNeeds() {
        return needs.toArray(new Need[0]);
    }

    public boolean addToBasket(Need need){
        // Check is real need that needs funding
        if(need != null && need.getAllFunded() == false){
            return needs.add(need);
        }
        return false;
    }

    // public List<Need> getFundingBasket() {
    //     return needs;
    // }

    public boolean removeFromBasket(Need need){
        return needs.remove(need);     
    } 
}
