package com.ufund.api.ufundapi.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Helper implements User {

    @JsonProperty("id")
    private final String id;
    @JsonProperty("username")
    private final String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("basket")
    private FundingBasket fundingBasket;

    private static final String HELPER_ID_MODIFIER = "H";

    public Helper(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString();
        this.fundingBasket = new FundingBasket();
    }

    public Helper(String username) {
        this.username = username;
        this.password = "";
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString();
        this.fundingBasket = new FundingBasket();
    }

    public Helper() {
        this.username = "";
        this.password = "";
        this.id = HELPER_ID_MODIFIER + UUID.randomUUID().toString();
        this.fundingBasket = new FundingBasket();
    }

    public boolean addToFundingBasket(Need need) {
        return fundingBasket.addToBasket(need);
    }

    public boolean removeFromFundingBasket(Need need) {
        return fundingBasket.removeFromBasket(need);
    }

    @JsonIgnore
    public Need[] getBasketNeeds() {
        return fundingBasket.getNeeds();
    }

    public String getPassword() {
        return this.password;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    // Clears funding basket 
    public boolean checkout() {
        return fundingBasket.checkout();
    }
}
