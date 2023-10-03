package com.ufund.api.ufundapi.controller; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("cupboard")
public class CupboardController {
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private Cupboard cupboard;
    private SearchController searchController;

    public CupboardController(Cupboard cupboard) {
        this.cupboard = cupboard;
    }

    @GetMapping("")
    public ResponseEntity<List<Need>> getEntireCupboard() {
        LOG.info("GET /cupboard");
            if (cupboard.isEmpty() == false){
                return new ResponseEntity<List<Need>>(cupboard.getEntireCupboard(),HttpStatus.OK);
            }   
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/search/?type=")
     public ResponseEntity<List<Need>> findNeedType(@PathVariable String type) {
        LOG.info("GET search/?type="+type);
        return new ResponseEntity<List<Need>>(searchController.findNeedType(type, cupboard),HttpStatus.OK);
    }
    @GetMapping("/search/?name=")
     public ResponseEntity<List<Need>> findNeedName(@PathVariable String name) {
        LOG.info("GET search/?name="+name);
        return new ResponseEntity<List<Need>>(searchController.findNeedName(name, cupboard),HttpStatus.OK);
    }
    @GetMapping("/search/?amount=")
     public ResponseEntity<List<Need>> findNeedAmount(@PathVariable double amount) {
        LOG.info("GET search/?amount="+amount);
        return new ResponseEntity<List<Need>>(searchController.findNeedAmount(amount, cupboard),HttpStatus.OK);
    }
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
