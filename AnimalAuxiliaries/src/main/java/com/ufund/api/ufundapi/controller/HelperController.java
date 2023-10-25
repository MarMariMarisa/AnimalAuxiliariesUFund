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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/{username}/{needID}")
    public ResponseEntity<Need> addToBasket(String username, String needID) {
        LOG.info("POST /funding-basket/" + username + "/" + needID);
        try{
            Need need = helperDAO.addToBasket(username, needID);
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
    public ResponseEntity<Need> removeFromBasket(String username, String needID) {
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
}