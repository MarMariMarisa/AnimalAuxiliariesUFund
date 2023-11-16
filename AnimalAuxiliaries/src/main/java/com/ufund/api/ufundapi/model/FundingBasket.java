package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class FundingBasket {
    // Private State
    @JsonProperty("needs")
    private List<Need> needs;

    // Constructor
    public FundingBasket() {
        needs = new ArrayList<>();
    }

    public Need[] getNeeds() {
        return needs.toArray(new Need[0]);
    }

    public boolean addToBasket(Need need) {
        // Check is real need that needs funding
        if (need != null && need.getAllFunded() == false) {
            if(needs.contains(need)){
                // If in basket already, update quantity 
                for(Need toUpdate : needs){
                    if(toUpdate.equals(need)){
                        toUpdate.setQuantity(need.getQuantity());
                        return true;
                    }
                }
            }
            else{
                return needs.add(need);
            }
            
        }
        return false;
    }

    public boolean removeFromBasket(Need need) {
        return needs.remove(need);
    }

    public boolean decrementNeedInBasket(Need helperNeed){
        if(needs.contains(helperNeed)){
            for(Need toUpdate : needs){
                if(toUpdate.equals(helperNeed)){
                    toUpdate.setQuantity(helperNeed.getQuantity());
                    return true;
                }
            }
        }
        return false;
    }

    // Purely empties the shopping cart
    public boolean checkout() {
        needs.clear();
        return true;
    }

    public List<Need> getBasket(){
        return needs;
    }    

}
