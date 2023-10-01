package com.ufund.api.ufundapi.model;

/**
 * Need.java
 * Description: Class to hold info surrounding a need the managers want to get funded 
 * 
 * @author - Chase Balmer
 */
public class Need {
    // Private State
    private String name;
    private String description;
    private String type;
    private double amount;
    private boolean isInBasket;
    private boolean isFunded;
    private String id;


    // Constructor
    public Need(String id,String name, String description, String type, double amount, boolean isInBasket, boolean isFunded) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.isInBasket = isInBasket;
        this.isFunded = isFunded;
    }

    // Methods
    /*
     * nameContains
     * Description: Determines whether the current needs name contains a string. Will be used for searching for needs
     * 
     * @param text 
     * @return boolean 
     */
    public boolean nameContains(String text){
        if (this.name != null && text != null) {
            return this.name.toLowerCase().contains(text.toLowerCase());
        }
        return false;
    }

    // Getter & Setters
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public boolean isInBasket() {
        return isInBasket;
    }
    public void setInBasket(boolean isInBasket) {
        this.isInBasket = isInBasket;
    }
    public boolean isFunded() {
        return isFunded;
    }
    public void setFunded(boolean isFunded) {
        this.isFunded = isFunded;
    }
}