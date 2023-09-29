package com.ufund.api.ufundapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Need.java
 * Description: Class that holds collection of needs for the organization.
 * 
 * @author - Chase Balmer
 */
public class Cupboard {
    // Private State
    private Map<String, Need> currentNeeds;     /*Needs that are not yet funded */
    private final int INITIAL_SIZE = 45;

    // Constructor 
    public Cupboard(){
        currentNeeds = new HashMap<>(INITIAL_SIZE);
    }

    // Methods
   /**
    * getNeed
    * Description: The function "getNeed" returns a specific need object from the cupboard
    * 
    * @param wantedNeed requested need
    * @return returns wanted need from the cupboard
    */
    public Need getNeed(Need wantedNeed){
        if(wantedNeed != null && wantedNeed.getName() != null){
            return currentNeeds.get(wantedNeed.getName());
        }
        return null;
    }

    /**
     * getNeedsOnName
     * Description: The function returns a list of needs whose names contain the given text
     * 
     * @param goalText Text to check for containment
     * @return The method is returning a List of Need objects that contain goalText
     */
    public List<Need> getNeedsOnName(String goalText){
        List<Need> matchingNeeds = new ArrayList<>();
        if(goalText != null){
            for(Need need : currentNeeds.values()){
                matchingNeeds.add(need);
            }
        }
        return matchingNeeds;
    }

    /**
     * getEntireCupboard
     * Description: The function returns the entire contents of the current cupboard as a map of strings to Need objects.
     * 
     * @return A map of strings to objects of type Need is being returned.
     */
    public Map<String, Need> getEntireCupboard(){
        return this.currentNeeds;
    }

    /**
     * addNeed
     * Description: The function adds a new need to a map of current needs. Will not add if new needs name is taken
     * 
     * @param newNeed Need to be added
     * @return boolean based on succes of adding
     */
    public boolean addNeed(Need newNeed){
        if(newNeed != null && !(this.currentNeeds.containsKey(newNeed.getName()))){
            this.currentNeeds.put(newNeed.getName(), newNeed);
            return true;
        }
        return false;
    }

    /**
     * This function removes a need from a collection of needs and returns true if the need was
     * successfully removed.
     * 
     * @param needName string representing the name of the need to be removed.
     * @return boolean based on success of removal
     */
    public boolean removeNeed(String needName){
        if(needName != null){
            Need removedNeed = currentNeeds.remove(needName);
            return removedNeed != null;
        }
        return false;
    }
}
