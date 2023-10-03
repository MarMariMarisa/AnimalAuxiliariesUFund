package com.ufund.api.ufundapi.controller; 

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.Cupboard;

/**
 * NeedController.java
 * Description: 
 * 
 * @author Sarah Payne (sap4735)
 */

 public class NeedController {

    public NeedController(){

    }
    /**
     * Creates a need and adds it to the given cupboard
     * 
     * @param name
     * @param description
     * @param type
     * @param amount
     * @param isInBasket
     * @param isFunded
     * @param cupboard
     */
    public void createNeed(String id,String name, String description, String type, double amount, boolean isInBasket, boolean isFunded, Cupboard cupboard) {
        Need need = new Need(id,name, description, type, amount, isInBasket, isFunded); 
        cupboard.addNeed(need); 
    }

    //TO-DO: I just feel like this could be implemented a little better 
    /**
     * updates the descriped Need in the given Cupboard 
     * creates a new Need if it does not exist in the Cupboard
     * 
     * @param name
     * @param description
     * @param type
     * @param amount
     * @param isInBasket
     * @param isFunded
     * @param cupboard
     */
    public void updateNeed(String originalName, String name, String description, String type, double amount, boolean isInBasket, boolean isFunded, Cupboard cupboard) {
        Need need = cupboard.getNeed(originalName);
        need.setName(name);
        need.setDescription(description);
        need.setType(type);
        need.setAmount(amount);
        need.setInBasket(isInBasket);
        need.setFunded(isFunded);  
        cupboard.addNeed(need);
    }


    //public retireNeed
    //TO-DO cannot implement because the cupboard class does not have a retired needs data structure 
    
    /**
     * removes the given Need from the given Cupboard 
     * 
     * @param needName
     * @param cupboard
     * @return true if the Need is sucessful removed, false if the Need does not exist in the specified Cupboard
     */
    public boolean removeNeed(String needName, Cupboard cupboard) {
        return cupboard.removeNeed(needName); 
    }

 }
