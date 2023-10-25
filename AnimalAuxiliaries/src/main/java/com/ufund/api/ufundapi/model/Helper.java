package com.ufund.api.ufundapi.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


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
        this.fundingBasket = new FundingBasket();
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString(); 
    }

    public Helper(){
        this.username = "";
        this.fundingBasket = new FundingBasket();
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString(); 
    }

    public boolean addToFundingBasket(Need need) {
        return fundingBasket.addToBasket(need);
    }

    public boolean removeFromFundingBasket(Need need) {
        return fundingBasket.removeFromBasket(need);
    }

    public Need[] getBasketNeeds() {
        return fundingBasket.getBasket().toArray(new Need[0]); 
    }

    public FundingBasket getFundingBasket() {
        return fundingBasket; 
    }

    public String getId() {
        return this.id; 
    }

    public String getUsername() {
        return this.username; 
    }
}