package com.ufund.api.ufundapi.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Helper implements User {
    
    @JsonProperty("id")
    private final String id;
    @JsonProperty("username")
    private final String username;

    private FundingBasket fundingBasket;

    private static final String HELPER_ID_MODIFIER = "H"; 

    public Helper(String username, FundingBasket basket) {
        this.username = username; 
        this.fundingBasket = basket; 
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString(); 
    }

    public boolean addToFundingBasket(Need need) {
        return fundingBasket.addToBasket(need);
    }

    public boolean removeFromFundingBasket(Need need) {
        return removeFromFundingBasket(need);
    }

    public String getId() {
        return this.id; 
    }

    public String getUsername() {
        return this.username; 
    }
}
