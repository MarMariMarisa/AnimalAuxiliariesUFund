package com.ufund.api.ufundapi.controller; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.CupboardDAO;
import com.ufund.api.ufundapi.persistence.NeedDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


@RestController
@RequestMapping("cupboard")
public class CupboardController {
    private static final Logger LOG = Logger.getLogger(NeedController.class.getName());
    private Cupboard cupboard;
    private SearchController searchController;


    public CupboardController(Cupboard cupboard) {
        this.cupboard = cupboard;
    }


    @GetMapping("/inventory")
    public ResponseEntity<List<Need>> getEntireCupboard() {
        LOG.info("GET /inventory");
            if (cupboard.isEmpty() == false){
                return new ResponseEntity<List<Need>>(cupboard.getEntireCupboard(),HttpStatus.OK);
            }   
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/search/?type={type}")
     public ResponseEntity<List<Need>> findNeedType(@PathVariable String type) {
        LOG.info("GET /?type="+type);
        return new ResponseEntity<List<Need>>(searchController.findNeedType(type, cupboard),HttpStatus.OK);
    }
    @GetMapping("/search/?name={name}")
     public ResponseEntity<List<Need>> findNeedName(@PathVariable String name) {
        LOG.info("GET /?name="+name);
        return new ResponseEntity<List<Need>>(searchController.findNeedName(name, cupboard),HttpStatus.OK);
    }
    @GetMapping("/search/?amount={amount}")
     public ResponseEntity<List<Need>> findNeedAmount(@PathVariable double amount) {
        LOG.info("GET /?amount="+amount);
        return new ResponseEntity<List<Need>>(searchController.findNeedAmount(amount, cupboard),HttpStatus.OK);
    }
        /**
     * Deletes a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNeed(@PathVariable String name) {
        LOG.info("DELETE /cupboard/" + name);
        if(cupboard.getNeed(name) != null){
            cupboard.removeNeed(name);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }
    }
