package com.ufund.api.ufundapi.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

// @JsonIgnoreProperties(ignoreUnknown = true)
public class Helper implements User {
    
    @JsonProperty("id")
    private final String id;
    @JsonProperty("username")
    private final String username;

    @JsonProperty("basket")
    private FundingBasket fundingBasket;

    private static final String HELPER_ID_MODIFIER = "H"; 

    public Helper(String username) {
        this.username = username;
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString(); 
        this.fundingBasket = new FundingBasket();
    }

    public Helper(){
        this.username = "";
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString();
        this.fundingBasket = new FundingBasket();
    }

    public boolean addToFundingBasket(Need need){
        return fundingBasket.addToBasket(need);
    }

    public boolean removeFromFundingBasket(Need need){
        return fundingBasket.removeFromBasket(need);   
    }
    
    @JsonIgnore 
    public Need[] getBasketNeeds() {
        return fundingBasket.getNeeds();
    }

    // @JsonUnwrapped
    // public FundingBasket getFundingBasket() {
    //     return fundingBasket;
    // }

    public String getId() {
        return this.id; 
    }

    public String getUsername() {
        return this.username; 
    }
}


