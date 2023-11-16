package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.HelperFileDAO; 

/*
 * Cupboard controller is still being used to add/remove stuff from the cupboard 
 * This class is going to be used for adding/removing/viewing from funding baskets 
 * 
 */
@RestController
@RequestMapping("funding-basket")
public class HelperController {
    private static final Logger LOG = Logger.getLogger(HelperController.class.getName()); 
    private HelperFileDAO helperDAO;

    public HelperController(HelperFileDAO helperDAO) throws IOException {
        this.helperDAO = helperDAO;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Need[]> getHelpersBasket(@PathVariable String username) {
        LOG.info("GET /funding-basket/" + username);
        try{
            return new ResponseEntity<Need[]>(helperDAO.getBasketNeeds(username), HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<Boolean> checkHelperCredentials(@PathVariable String username, @PathVariable String password){
        LOG.info("GET /fundingbasket/" + username + "/" + password);
        return new ResponseEntity<Boolean>(helperDAO.checkCredentials(username, password), HttpStatus.OK);
       
    }

    @PostMapping("/{username}")
    public ResponseEntity<Need> addToBasket(@PathVariable String username, @RequestBody String needJson) {

        LOG.info("POST /funding-basket/" + username + "/" + needJson);

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Need need = objectMapper.readValue(needJson, Need.class);
            Need nee = helperDAO.addToBasket(username, need);
            

            if(nee != null){
                return new ResponseEntity<>(nee, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(need, HttpStatus.CONFLICT);
            }
            
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("")
    public ResponseEntity<Helper> createHelper(@RequestBody String helperJson) {
            LOG.info("POST /funding-basket " + helperJson);
            try{
                ObjectMapper objectMapper = new ObjectMapper();
                Helper aHelper = objectMapper.readValue(helperJson, Helper.class);
                Helper helper = new Helper(aHelper.getUsername(),aHelper.getPassword());
                if(helperDAO.createHelper(helper) != null){
                    return new ResponseEntity<Helper>(helper, HttpStatus.CREATED);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            catch(IOException e){
                LOG.log(Level.SEVERE,e.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @PostMapping("/{username}/{needID}")
    public ResponseEntity<Need> decrementNeedQuantity(@PathVariable String username, @PathVariable String needID){
        LOG.info("POST /funding-basket/" + username + "/" + needID);
        try{
            Need need = helperDAO.decrementNeedInBasket(username, needID);
            if(need != null){
                return new ResponseEntity<>(need, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(need, HttpStatus.CONFLICT);
            }
            
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}/{needID}")
    public ResponseEntity<Need> removeFromBasket(@PathVariable String username, @PathVariable String needID) {
        LOG.info("DELETE /funding-basket/" + username + "/" + needID);
        try{
            Need need = helperDAO.removeFromBasket(username, needID);
            if(need != null){
                return new ResponseEntity<>(need, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(need, HttpStatus.CONFLICT);
            }
            
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Boolean> checkout(@PathVariable String username) {
        LOG.info("DELETE/FUND /funding-basket/" + username );
        try{
            return new ResponseEntity<Boolean>(helperDAO.checkout(username), HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Boolean> adoptAnimal(@PathVariable String animalId){
        LOG.info("PUT /adopt/" + animalId);
        try{
            return new ResponseEntity<Boolean>(helperDAO.adoptAnimal(animalId), HttpStatus.OK);
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}