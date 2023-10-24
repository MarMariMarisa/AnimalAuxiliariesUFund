package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Cupboard.java
 * Description: Class that holds collection of needs for the organization.
 */
@Component
public class Cupboard {

    // Private State
    @JsonProperty("currentNeeds")
    private Map<String, Need> currentNeeds;
    @JsonProperty("retiredNeeds")
    private Map<String, Need> retiredNeeds;
    private static final int INITIAL_MAP_SIZE = 45;

    // Constructor
    public Cupboard() {
        currentNeeds = new HashMap<>(INITIAL_MAP_SIZE);
        retiredNeeds = new HashMap<>(INITIAL_MAP_SIZE); 
    }

    // Methods
    /**
     * getNeedOnID
     * Description: The function "getNeed" returns a specific need object from the cupboard
     * 
     * @param wantedNeed requested need
     * @return returns wanted need from the cupboard
     */
    public Need getNeedOnID(String id) {
        if (id != null) {
            return currentNeeds.get(id);
        }
        return null;
    }

    public boolean isEmpty() {
        return currentNeeds.values().size() == 0;
    }

    /**
     * getNeedsOnName
     * Description: The function returns a list of needs whose names contain the
     * given text
     * 
     * @param nameSubstring Text to check for containment
     * @return The method is returning a List of Need objects that contain goalText
     */
    public List<Need> getNeedsOnName(String nameSubstring) {
        List<Need> matchingNeeds = new ArrayList<>();
        if (nameSubstring != null) {
            for (Need need : currentNeeds.values()) {
                if (need.getName().toLowerCase().contains(nameSubstring.toLowerCase())) {
                    matchingNeeds.add(need);
                }
            }
        }
        return matchingNeeds;
    }

    /**
     * getEntireCupboard
     * Description: The function returns the entire contents of the current cupboard
     * as a map list of need objects.
     * 
     * @return A list of objects of type Need is being returned.
     */
    public List<Need> getEntireCupboard() {
        return new ArrayList<Need>(currentNeeds.values());
    }

    /**
     * addNeed
     * Description: The function adds a new need to a map of current needs. Will not
     * add if ID is taken
     * 
     * @param newNeed Need to be added
     * @return boolean based on succes of adding
     */
    public boolean addNeed(Need newNeed) {
        if (newNeed != null && !(this.currentNeeds.containsKey(newNeed.getId()))) {
            this.currentNeeds.put(newNeed.getId(), newNeed);
            return true;
        }
        return false;
    }
    /**
     * 
     */
    public boolean updateNeed(Need need) {
        if (currentNeeds.containsKey(need.getId())) {
            currentNeeds.put(need.getId(), need);
            return true;
        }
        return false;
    }

    /**
     * This function removes a need from a collection of needs and "retires" it so that it
     * remains stored while it is no longer being used
     * 
     * @param needID 
     * @return boolean based on success of removal
     */
    public boolean retireNeed(String needId) {
        if (needId != null) {
            Need removedNeed = currentNeeds.remove(needId);
            if(removedNeed != null) {
                retiredNeeds.put(needId, removedNeed); 
                return true; 
            }
        }
        return false;
    }
    public List<Need> getRetiredNeeds() {
        return new ArrayList<Need>(retiredNeeds.values());
    }
    @Override
    public String toString() {
        return String.format(getEntireCupboard().toString());
    }
}
