package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.model.AdoptableAnimal;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.AdoptableAnimalDAO;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

public class AdoptionCupboardControllerTest {

    private AdoptionCupboardController adoptionCupboardController; 
    private AdoptableAnimalDAO mockAnimalDAO; 

    @BeforeEach 
    public void setupAnimalController() throws IOException {
        mockAnimalDAO = mock(AdoptableAnimalDAO.class); 
        adoptionCupboardController = new AdoptionCupboardController(mockAnimalDAO); 
    }

    @Test 
    public void testGetAnimal() throws IOException {
        AdoptableAnimal animal = new AdoptableAnimal("Test Animal", "Description", "Species"); 
        animal.setId("new id"); 
        when(mockAnimalDAO.getAnimal("new id")).thenReturn(animal); 
        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.getAnimal("new id"); 
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
    }
    
    @Test 
    public void testGetAnimalNotFound() throws IOException {
        String animalId = "10"; 
        when(mockAnimalDAO.getAnimal(animalId)).thenReturn(null); 
        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.getAnimal(animalId); 
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    }

    @Test 
    public void testGetAnimals() throws IOException {
        AdoptableAnimal[] animals = new AdoptableAnimal[2]; 
        animals[0] = new AdoptableAnimal("Animal 1", "Description", "Species");
        animals[1] = new AdoptableAnimal("Animal 2", "Description", "Species"); 
        when(mockAnimalDAO.getAnimals()).thenReturn(animals); 

        ResponseEntity<AdoptableAnimal[]> response = adoptionCupboardController.getAnimals(); 

        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        assertEquals(response.getBody(), animals); 

    }

    @Test 
    public void testGetAnimalsHandleException() throws IOException {
        doThrow(new IOException()).when(mockAnimalDAO).getAnimals(); 

        ResponseEntity<AdoptableAnimal[]> response = adoptionCupboardController.getAnimals(); 

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()); 
    }

    @Test 
    public void testGetAnimalOnSpecies() throws IOException {
        AdoptableAnimal[] animals = new AdoptableAnimal[2]; 
        animals[0] = new AdoptableAnimal("Animal 1", "Description", "Species"); 
        animals[1] = new AdoptableAnimal("Animal 2", "Description", "Species"); 
        String search = "sp"; 
        when(mockAnimalDAO.findAnimalOnSpecies(search)).thenReturn(animals); 
        
        ResponseEntity<AdoptableAnimal[]> response = adoptionCupboardController.getAnimalOnSpecies(search); 

        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        assertEquals(animals, response.getBody()); 
    }

    @Test 
    public void testUpdateAnimal() throws IOException {
        AdoptableAnimal animal = new AdoptableAnimal("Animal", "Description", "Species"); 
        when(mockAnimalDAO.updateAnimal(animal)).thenReturn(animal); 

        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.updateAnimal(animal); 
        animal.setName("New Animal Name"); 

        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        assertEquals(animal, response.getBody()); 

    }

    @Test 
    public void testUpdateAnimalNotFound() throws IOException {
        AdoptableAnimal animal = new AdoptableAnimal("Animal Name", "Description", "Species"); 
        when(mockAnimalDAO.updateAnimal(animal)).thenReturn(null); 

        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.updateAnimal(animal); 

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    }

    @Test 
    public void testDeleteAnimal() throws IOException {
        
    }
}
