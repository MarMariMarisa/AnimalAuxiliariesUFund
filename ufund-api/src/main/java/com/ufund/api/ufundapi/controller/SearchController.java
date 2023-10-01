package com.ufund.api.ufundapi.controller;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;
import java.util.List;
import java.util.ArrayList;

/**
 * SearchController.java
 * Description:
 * 
 * @author Tyler Combs (tec8354)
 */

public class SearchController {
    
    /**
     * findNeedType
     * Description: Searches entire cupboard and returns need matching the type specified
     * 
     * @param type string representing the type of need to be searched for
     * @param cupboard
     * @return
     */
    public List<Need> findNeedType(String type, Cupboard cupboard){
        List<Need> toReturn = new ArrayList<>();
        for(Need need : cupboard.getEntireCupboard()){
            if(need.getType() == type){
                toReturn.add(need);
            }
        }
        if(toReturn.size() == 0){
            return null;
        }
        return toReturn;
    }

    /**
     * findNeedName
     * Description: Searches entire cupboard and returns need matching the name specified
     * 
     * @param name string representing the name of the need to be searched for
     * @param cupboard the cupboard being searched
     * @return retunrs the first need found matching the name. otherwise returns nothing
     */
    public List<Need> findNeedName(String name, Cupboard cupboard){
        List<Need> toReturn = new ArrayList<>();
        for(Need need : cupboard.getEntireCupboard()){
            if(need.getName().contains(name)){
                toReturn.add(need);
            }
        }
         if(toReturn.size() == 0){
            return null;
        }
        return toReturn;
    }

    /**
     * findNeedAmount
     * Description: Searches entire cupboard and returns need matching the amount specified
     * 
     * @param amount double value associated with the cost of a need to be searched for
     * @param cupboard
     * @return
     */
    public List<Need> findNeedAmount(double amount, Cupboard cupboard){
        List<Need> toReturn = new ArrayList<>();
        for(Need need : cupboard.getEntireCupboard()){
            if(need.getAmount() == amount){
                toReturn.add(need);
            }
        }
        return toReturn;
    }

    /**
     * findNeedStatus
     * Description: Searches entire cupboard and returns need matching status specified
     * 
     * @param status boolean value associated with a need being a basket
     * @param cupboard 
     * @return
     */
    public List<Need> findNeedStatus(boolean status, Cupboard cupboard){
        List<Need> toReturn = new ArrayList<>();
        for(Need need : cupboard.getEntireCupboard()){
            if(need.isInBasket() == status){
                toReturn.add(need);
            }
        }
        if(toReturn.size() == 0){
            return null;
        }
        return toReturn;
    }
}
