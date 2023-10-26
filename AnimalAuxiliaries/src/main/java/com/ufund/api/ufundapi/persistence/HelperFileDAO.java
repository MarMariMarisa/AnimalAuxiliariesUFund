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
        Helper[] helperArray = getHelpers();


        // objectMapper.writeValue(new File(filename), helperArray);
        // return true;
        String json = objectMapper.writeValueAsString(helperArray);
        System.out.println("JSON content to save: " + json);

        objectMapper.writeValue(new File(filename), helperArray);
        return true;
    }

    public Helper[] getHelpers(){
        synchronized(helpers){
            return helpers.values().toArray(new Helper[0]);
        }
        
    }

    // public Helper getHelper(String username) throws IOException{
    //     synchronized(helpers){
    //         return helpers.get(username);
    //     }
    // }

    public Helper createHelper(Helper helper) throws IOException{
        synchronized(helpers){
            if(!helpers.containsKey(helper.getUsername())){
                helpers.put(helper.getUsername(), helper);
                save();
                return helper;
            }
            return null;
        }
    }

    public Need addToBasket(String username, Need need) throws IOException{
        synchronized(helpers){
            if(helpers.containsKey(username)){
                Helper h = helpers.get(username);
                if(need != null){
                    need.setNumInBaskets(need.getNumInBaskets()+1);
                    if(h.addToFundingBasket(need)){
                        needDao.updateNeed(need); 
                        save();   
                        return need;          
                    }
                    else{
                        need.setNumInBaskets(need.getNumInBaskets()-1);
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
                        need.setNumInBaskets(need.getNumInBaskets()-1);
                        needDao.updateNeed(need);  
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

    public Need[] getBasketNeeds(String username) throws IOException{
        synchronized(helpers){
            Helper h = helpers.get(username);
            if(h != null){
                return h.getBasketNeeds();
            }
            return null;
            
        }
    }
}
