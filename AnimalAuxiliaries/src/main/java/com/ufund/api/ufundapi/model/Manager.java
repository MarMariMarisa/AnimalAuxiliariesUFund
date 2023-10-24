package com.ufund.api.ufundapi.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Manager implements User {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("username")
    private final String username;

    private Cupboard cupboard;

    private static final String MANAGER_ID_MODIFIER = "M";

    public Manager(String username, Cupboard cupboard) {
        this.username = username;
        this.cupboard = cupboard;
        this.id = MANAGER_ID_MODIFIER + UUID.randomUUID().toString();
    }

    //view cupboard
    /**
     * 
     * @return a list of all needs in the cupboard
     */
    public List<Need> viewCupboard(){
        return cupboard.getEntireCupboard();
    }

    //search
    /**
     * 
     * @param substring string to search through
     * @return list of needs containing the string
     */
    public List<Need> searchNeeds(String substring) {
        return cupboard.getNeedsOnName(substring);
    }

    //select
    /**
     * 
     * @param id string representing the need id
     * @return the need in cupboard matching the id
     */
    public Need getNeed(String id){
        return cupboard.getNeedOnID(id);
    }

    //edit -> should also remove from any helper's funding basket
    /**
     * 
     * @param need the need being updated
     * @return boolean value if need is succesfully updated
     */
    public boolean updateNeed(Need need){
        return cupboard.updateNeed(need);
    }

    //retire -> should also remove from any helper's funding basket
    /**
     * 
     * @param needId id of the need to be retired
     * @return boolean value if the need is successfully retired
     */
    public boolean retireNeed(String needId){
        return cupboard.retireNeed(needId);
    }

    //unretire
    /**
     * 
     * @param needId id of the need to be unretired
     * @return boolean value if the need is successfully unretired
     */
    public boolean unretireNeed(String needId){
        return cupboard.unretireNeed(needId);
    }

    //create
    /**
     * 
     * @param name 
     * @param description
     * @param type
     * @param price
     * @param quantity
     * @return boolean value if the need is succesfully created and added to the cupboard
     */
    public boolean createNewNeed(String name, String description, String type, float price, int quantity){
        Need newneed = new Need(name, description, type, price, quantity);
        return cupboard.addNeed(newneed);
    }

    /**
     * @return returns Manager id
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * @return returns Manager username
     */
    @Override
    public String getUsername() {
        return this.username;
    }
}