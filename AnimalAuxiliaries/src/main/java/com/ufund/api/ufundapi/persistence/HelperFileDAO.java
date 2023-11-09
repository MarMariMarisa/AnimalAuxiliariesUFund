package com.ufund.api.ufundapi.persistence;

import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper; 

@Component
public class HelperFileDAO implements UserDAO {
    // Private state
    private Map<String, Helper> helpers; //Username, helper object
    private ObjectMapper objectMapper; 
    private String filename; 
    private NeedFileDAO needDao;


    public HelperFileDAO(@Value("${helpers.file}") String filename, ObjectMapper objectMapper, NeedFileDAO needFileDAO) throws IOException {
        this.needDao = needFileDAO;
        this.filename = filename;
        this.objectMapper = objectMapper;
        load(); 
    }

    private boolean load() throws IOException {
        helpers = new HashMap<String, Helper>();

        Helper[] helperArray = objectMapper.readValue(new File(filename), Helper[].class);
    
        for(Helper h : helperArray){
            if(h != null){
                helpers.put(h.getUsername(), h);
            }
            
        }

        return true;
    }

    private boolean save() throws IOException {
        try{
            Helper[] helperArray = getHelpers();
            

        // objectMapper.writeValue(new File(filename), helperArray);
        // return true;
        String json = objectMapper.writeValueAsString(helperArray);
        System.out.println("JSON content to save: " + json);

        objectMapper.writeValue(new File(filename), helperArray);
        return true;
        }
        catch(IOException e){
            return false;
        }
        
    }


    public Helper[] getHelpers(){
        synchronized(helpers){
            return helpers.values().toArray(new Helper[0]);
        }
        
    }

    public boolean checkCredentials(String username, String password){
        synchronized(helpers){
           
            for(Helper h : getHelpers()){
                
                boolean usernameMatch = h.getUsername().equals(username);
                if(usernameMatch){
                    boolean passwordMatch = h.getPassword().equals(password);
                    if(passwordMatch){
                        return true;
                    }  
                }
            }
        }
        return false;
    }

    public Helper createHelper(Helper helper) throws IOException{
        synchronized(helpers){
            if(!helpers.containsKey(helper.getUsername())){
                helpers.put(helper.getUsername(), helper);

                String json = objectMapper.writeValueAsString(helper);
                System.out.println("Helper:" + json);

                save();
                return helper;
            }
            return null;
        }
    }

    public Need addToBasket(String username, Need need) throws IOException{
        synchronized(helpers){
            if(helpers.containsKey(username)){
                // Setup
                Helper h = helpers.get(username);
                Need helperNeed;
                
                // Need Viability
                if(need != null && need.getQuantity() >= 0){
                    // Need is already in basket 
                    for(Need n : h.getBasketNeeds()){
                        if(need.equals(n)){
                            helperNeed = new Need(n);

                            // Check if need at max cap
                            if(helperNeed.getQuantity() >= need.getQuantity()){
                                return null;
                            }

                            // Add updated need to basket 
                            helperNeed.setQuantity(helperNeed.getQuantity()+1);
                            if(h.removeFromFundingBasket(n)){
                                if(h.addToFundingBasket(helperNeed)){
                                    // cupboardNeed.setQuantity(cupboardNeed.getQuantity());
                                    // needDao.updateNeed(cupboardNeed); 
                                    save();   
                                    return need;          
                                }
                            }
                            return null;
                        }   
                    }

                    // Need not present in basket
                    helperNeed = new Need(need);
                    helperNeed.setQuantity(1);
                    if(h.addToFundingBasket(helperNeed)){
                        // need.setQuantity(need.getQuantity());
                        // needDao.updateNeed(need); 
                        save();   
                        return need;          
                    }
                    else{
                        return null;
                    }
                }
                
            }
            return null;
        }
    }

    public Need removeFromBasket(String username, String needID) throws IOException{
        synchronized(helpers){
            if(helpers.containsKey(username)){
                Helper h = helpers.get(username);
                Need need = needDao.getNeed(needID);
     
                if(need != null){
                    if(h.removeFromFundingBasket(need)){
                        save();  
                        return need;
                    }
                    else{
                        return null;
                    }
                }
                else{
                    for(Need n : h.getBasketNeeds()){
                        if(n.getId().equals(needID)){
                            if(h.removeFromFundingBasket(n)){
                                save();
                                return n;
                            }
                            else
                                return null;
                        }                       
                    }
                }     
            }
            return null;
        }
    }

    public Need[] getBasketNeeds(String username) throws IOException{
        synchronized(helpers){
            Helper h = helpers.get(username);
            if(h != null){
                return h.getBasketNeeds();
            }
            return null;
            
        }
    }

    // public boolean checkout(String username, int surplus) throws IOException{
    //     synchronized(helpers){
    //         Helper helper = helpers.get(username);
    //         if(helper != null){
    //             save();
    //             return helper.checkout();      
    //         }
    //         return false;
    //     }
    // }
}
