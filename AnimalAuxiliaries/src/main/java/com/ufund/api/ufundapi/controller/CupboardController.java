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

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("cupboard")
public class CupboardController {
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private NeedFileDAO needDAO;

    public CupboardController(NeedFileDAO needDAO) throws IOException {
        this.needDAO = needDAO;
    }
    
    @GetMapping("")
    public ResponseEntity<Need[]> getEntireCupboard() {
        LOG.info("GET /cupboard");
        try{
            return new ResponseEntity<Need[]>(needDAO.getNeeds(), HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Need> getNeed(@PathVariable String id) throws IOException{
        LOG.info("GET /cupboard/"+id);
        try{
            Need need = needDAO.getNeed(id);
            if(need != null){
                return new ResponseEntity<Need>(need, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        //for(Need i : cupboard.getEntireCupboard()) if(i.getId()== id) return new ResponseEntity<Need>(i,HttpStatus.OK);
        
    }
    /**
     * @GetMapping("/retired")
    public ResponseEntity<List<Need>> getRetiredNeeds() {
        LOG.info("GET /retired");
        if (cupboard.isEmpty() == false) {
            return new ResponseEntity<List<Need>>(cupboard.getRetiredNeeds(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
     * 
     */
    
    
    @GetMapping("/")
    public ResponseEntity<Need[]> searchOnName(@RequestParam String name) {
        LOG.info("GET /?name=" + name);
        try{
            return new ResponseEntity<Need[]>(needDAO.findNeeds(name), HttpStatus.OK);
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<Need> updateNeed(@RequestParam Need need) {
        LOG.info("PUT /cupboard " + need.getId());
        try{
            if(needDAO.updateNeed(need) == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Need>(need, HttpStatus.OK);
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNeed(@PathVariable String id) {
        LOG.info("DELETE /cupboard/" + id);
        try{
            if(needDAO.deleteNeed(id))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @PostMapping("")
   public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /cupboard " + need.getID());
        try{
            if(needDAO.getNeed(need.getId()) == null){
                if(needDAO.createNeed(need) == null)
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                else
                    return new ResponseEntity<Need>(need, HttpStatus.CREATED);
            }
            else    
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}