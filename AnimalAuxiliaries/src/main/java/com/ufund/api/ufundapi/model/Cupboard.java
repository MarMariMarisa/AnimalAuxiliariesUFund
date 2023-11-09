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
    @JsonProperty("fundedNeeds")
    private Map<String, Need> fundedNeeds;
    @JsonProperty("surplus")
    private float surplus; 

    private static final int INITIAL_MAP_SIZE = 45;
    private static final float INITIAL_SURPLUS = 0.0f; 

    // Constructor
    public Cupboard() {
        currentNeeds = new HashMap<>(INITIAL_MAP_SIZE);
        fundedNeeds = new HashMap<>(INITIAL_MAP_SIZE); 
        surplus = INITIAL_SURPLUS; 
    }

    /**
     * 'funds' all needs in the list by removing them from the current needs and adding them to funded needs 
     * fails and returns false if any one of the needs in the list did not exist in currentNeeds before the method is called 
     * a failure will result in no change to the cupboard 
     * 
     * @param needs List<Need>
     * @return boolean success
     */
    public boolean fundNeeds(List<Need> needs) {
        //check that all needs are in the cupboard 
        for(Need need : needs) {
            if(currentNeeds.get(need.getId()) == null) 
                return false; 
        }

        //then remove them from current and add them to funded
        for(Need need : needs) {
            currentNeeds.remove(need.getId());
            fundedNeeds.put(need.getId(), need); 
        }

        return true; 
            
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

    public List<Need> getFundedNeeds() {
        return new ArrayList<Need>(fundedNeeds.values()); 
    }

    /**
     * retrieves the current surplus value 
     * 
     * @return float
     */
    public float getSurplus() {
        return surplus; 
    }

    //!! This method exists for use in the cash out surplus functionality!!
    /**
     * resets the surplus value to zero 
     */
    public void resetSurplus() {
        surplus = INITIAL_SURPLUS; 
    }

    /**
     * adds the given amount to the current surplus value 
     * @param amt
     */
    public void addToSurplus(float amt) {
        surplus += amt; 
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
     * deletes a need from the cupboard by removing it from the currentNeeds list
     * @param need
     * @return boolean success 
     */
    public boolean deleteNeed(String needID) {
        if(needID != null) {
            if(currentNeeds.remove(needID) != null) {
                return true; 
            }
        }
        return false; 
    }

    
}
