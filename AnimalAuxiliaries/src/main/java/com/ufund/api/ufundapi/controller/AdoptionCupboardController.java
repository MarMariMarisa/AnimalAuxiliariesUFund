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

import com.ufund.api.ufundapi.model.AdoptableAnimal;
import com.ufund.api.ufundapi.persistence.AdoptableAnimalDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger; 

@RestController
@RequestMapping("adoptioncupboard")
public class AdoptionCupboardController {

    private static final Logger LOG = Logger.getLogger(AdoptionCupboardController.class.getName()); 
    private AdoptableAnimalDAO animalDAO; 

    public AdoptionCupboardController(AdoptableAnimalDAO animalDAO) throws IOException {
        this.animalDAO = animalDAO; 
    }

    @GetMapping("")
    public ResponseEntity<AdoptableAnimal[]> getAnimals() {
        LOG.info("GET /adoptioncupboard");
        try {
            return new ResponseEntity<AdoptableAnimal[]>(animalDAO.getAnimals(), HttpStatus.OK); 
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @PutMapping("")
    public ResponseEntity<AdoptableAnimal> updateAnimal(@RequestBody AdoptableAnimal animal) {
        LOG.info("PUT /adoptioncupboard " + animal.getId()); 
        try {
            if(animalDAO.updateAnimal(animal) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            } else {
                return new ResponseEntity<AdoptableAnimal>(animal, HttpStatus.OK); 
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptableAnimal> getAnimal(@PathVariable String id) throws IOException {
        LOG.info("GET /adoptioncupboard/" + id); 
        try {
            AdoptableAnimal animal = animalDAO.getAnimal(id); 
            if(animal != null) {
                return new ResponseEntity<AdoptableAnimal>(animal, HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @GetMapping("/")
    public ResponseEntity<AdoptableAnimal[]> getAnimalOnSpecies(@RequestParam String species) {
        LOG.info("GET /?species=" + species); 
        try {
            return new ResponseEntity<AdoptableAnimal[]>(animalDAO.findAnimalOnSpecies(species), HttpStatus.OK); 
        } catch(IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAnimal(@PathVariable String id) {
        LOG.info("DELETE /adoptioncupboard/" + id); 
        try {
            if(animalDAO.deleteAnimal(id)) {
                return new ResponseEntity<>(HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @PostMapping("")
    public ResponseEntity<AdoptableAnimal> createAnimal(@RequestBody AdoptableAnimal animal) {
        LOG.info("POST /adoptioncupboard " + animal.getId()); 

        if(animal.getId().equals("")) {
            animal = new AdoptableAnimal(animal.getName(), animal.getDescription(), animal.getSpecies()); 
        }

        try {
            if(animalDAO.getAnimal(animal.getId()) == null) {
                if(animalDAO.createAnimal(animal) == null) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT); 
                } else {
                    return new ResponseEntity<AdoptableAnimal>(animal, HttpStatus.CREATED); 
                }
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT); 
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}
