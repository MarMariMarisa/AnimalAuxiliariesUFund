package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.List;

import com.ufund.api.ufundapi.model.Need;

public class FundingBasket {
    // Private State
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
        if(need != null & (need.getAllFunded() == false)){
            return needs.add(need);
        }
        return false;
    }

    public boolean removeFromBasket(Need need){
        return needs.remove(need);     
    }
}
