package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.model.AdoptableAnimal;
import com.ufund.api.ufundapi.persistence.AdoptableAnimalDAO;

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

    // @Test 
    // public void testUpdateAnimal() throws IOException {
    //     AdoptableAnimal animal = new AdoptableAnimal("Animal", "Description", "Species"); 
    //     when(mockAnimalDAO.updateAnimal(animal)).thenReturn(animal); 

    //     ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.updateAnimal(animal); 
    //     animal.setName("New Animal Name"); 

    //     assertEquals(HttpStatus.OK, response.getStatusCode()); 
    //     assertEquals(animal, response.getBody()); 

    // }

    // @Test 
    // public void testUpdateAnimalNotFound() throws IOException {
    //     AdoptableAnimal animal = new AdoptableAnimal("Animal Name", "Description", "Species"); 
    //     when(mockAnimalDAO.updateAnimal(animal)).thenReturn(null); 

    //     ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.updateAnimal(animal); 

    //     assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    // }

    @Test
    public void testDeleteAnimal() throws IOException {
        String animalId = "123";
        when(mockAnimalDAO.deleteAnimal(animalId)).thenReturn(true);

        ResponseEntity<HttpStatus> response = adoptionCupboardController.deleteAnimal(animalId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteAnimalFail() throws IOException {
        String animalId = "456";
        when(mockAnimalDAO.deleteAnimal(animalId)).thenReturn(false);

        ResponseEntity<HttpStatus> response = adoptionCupboardController.deleteAnimal(animalId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteAnimalIOException() throws IOException {
        String animalId = "789";
        when(mockAnimalDAO.deleteAnimal(animalId)).thenThrow(new IOException("Simulated IOException"));
        ResponseEntity<HttpStatus> response = adoptionCupboardController.deleteAnimal(animalId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateAnimal() throws IOException {
        AdoptableAnimal newAnimal = new AdoptableAnimal("New Animal", "Description", "Species");
        
        when(mockAnimalDAO.getAnimal(anyString())).thenReturn(null);
        when(mockAnimalDAO.createAnimal(any(AdoptableAnimal.class))).thenReturn(newAnimal);

        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.createAnimal(newAnimal);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newAnimal, response.getBody());

        AdoptableAnimal mockAnimal = mock(AdoptableAnimal.class); 
        when(mockAnimal.getId()).thenReturn("");
        response = adoptionCupboardController.createAnimal(mockAnimal);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        when(mockAnimalDAO.getAnimal(newAnimal.getId())).thenReturn(null);
        when(mockAnimalDAO.createAnimal(newAnimal)).thenReturn(null);
        response = adoptionCupboardController.createAnimal(newAnimal);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    

    @Test
    public void testCreateAnimalConflict() throws IOException {
        AdoptableAnimal existingAnimal = new AdoptableAnimal("Existing Animal", "Description", "Species");

        when(mockAnimalDAO.getAnimal(existingAnimal.getId())).thenReturn(existingAnimal);

        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.createAnimal(existingAnimal);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateAnimalIOE() throws IOException {
        AdoptableAnimal newAnimal = new AdoptableAnimal("New Animal", "Description", "Species");

        when(mockAnimalDAO.getAnimal(anyString())).thenReturn(null);
        when(mockAnimalDAO.createAnimal(any(AdoptableAnimal.class))).thenThrow(new IOException("Simulated IOException"));

        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.createAnimal(newAnimal);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetAnimalOnSpeciesIOE() throws IOException {
        String species = "Dog";

        when(mockAnimalDAO.findAnimalOnSpecies(species)).thenThrow(new IOException("Simulated IOException"));

        ResponseEntity<AdoptableAnimal[]> response = adoptionCupboardController.getAnimalOnSpecies(species);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetAnimal_IOError() throws IOException {
        String animalId = "123";

        when(mockAnimalDAO.getAnimal(animalId)).thenThrow(new IOException("Simulated IOException"));

        ResponseEntity<AdoptableAnimal> response = adoptionCupboardController.getAnimal(animalId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}