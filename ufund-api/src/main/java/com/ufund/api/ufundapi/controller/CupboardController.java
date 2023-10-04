package com.ufund.api.ufundapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("cupboard")
public class CupboardController {
    private static final Logger LOG = Logger.getLogger(NeedController.class.getName());
    private Cupboard cupboard;
    private SearchController searchController = new SearchController();
    private NeedFileDAO needDAO;

    public CupboardController(Cupboard cupboard, NeedFileDAO needDAO) throws IOException {
        this.cupboard = cupboard;
        this.needDAO = needDAO;
        for (Need need : needDAO.getNeeds()) {
            cupboard.addNeed(need);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Need>> getEntireCupboard() {
        LOG.info("GET /cupboard");
        if (cupboard.isEmpty() == false) {
            return new ResponseEntity<List<Need>>(cupboard.getEntireCupboard(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }  

    //@GetMapping("/search/?name=")
    @GetMapping("/")
    public ResponseEntity<List<Need>> searchOnName(@RequestParam String name) {
        LOG.info("GET /?name=" + name);
            return new ResponseEntity<List<Need>>(searchController.findNeedName(name, cupboard), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Need> updateNeed(@RequestBody Need need) {
        LOG.info("PUT /cupboard " + need);
        if(cupboard.updateNeed(need)){
            return new ResponseEntity<Need>(need, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteNeed(@RequestParam String name) {
        LOG.info("DELETE /?name=" + name);
        if (cupboard.getNeed(name) != null) {
            if(cupboard.removeNeed(name))
                return new ResponseEntity<>(HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    }

    @PostMapping("")
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /cupboard " + need);
            if (cupboard.getNeed(need.getName()) == null) {
                if(cupboard.addNeed(need)){
                    return new ResponseEntity<Need>(need, HttpStatus.CREATED);
                }
                    
            }
            return new ResponseEntity<Need>(need, HttpStatus.CONFLICT);
    }

}
