package com.ufund.api.ufundapi.controller;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;

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
    public Need findNeedType(String type, Cupboard cupboard){
        for(Need need : cupboard.getEntireCupboard().values()){
            if(need.getType() == type){
                return need;
            }
        }
        return null;
    }

    /**
     * findNeedName
     * Description: Searches entire cupboard and returns need matching the name specified
     * 
     * @param name string representing the name of the need to be searched for
     * @param cupboard the cupboard being searched
     * @return retunrs the first need found matching the name. otherwise returns nothing
     */
    public Need findNeedName(String name, Cupboard cupboard){
        for(Need need : cupboard.getEntireCupboard().values()){
            if(need.getName() == name){
                return need;
            }
        }
        return null;
    }

    /**
     * findNeedAmmount
     * Description: Searches entire cupboard and returns need matching the ammount specified
     * 
     * @param ammount double value associated with the cost of a need to be searched for
     * @param cupboard
     * @return
     */
    public Need findNeedAmount(double amount, Cupboard cupboard){
        for(Need need : cupboard.getEntireCupboard().values()){
            if(need.getAmount() == amount){
                return need;
            }
        }
        return null;
    }

    /**
     * findNeedStatus
     * Description: Searches entire cupboard and returns need matching status specified
     * 
     * @param status boolean value associated with a need being a basket
     * @param cupboard 
     * @return
     */
    public Need findNeedStatus(boolean status, Cupboard cupboard){
        for(Need need : cupboard.getEntireCupboard().values()){
            if(need.isInBasket() == status){
                return need;
            }
        }
        return null;
    }
}
