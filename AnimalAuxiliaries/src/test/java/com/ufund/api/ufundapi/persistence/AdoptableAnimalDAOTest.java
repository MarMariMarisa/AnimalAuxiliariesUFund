package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.AdoptableAnimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Persistence-tier")
public class AdoptableAnimalDAOTest {
    AdoptableAnimalDAO animalDAO;
    AdoptableAnimal[] testAnimals; 
    ObjectMapper mockObjectMapper; 

    @BeforeEach 
    public void setupAnimalDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class); 
        testAnimals = new AdoptableAnimal[3]; 

        testAnimals[0] = new AdoptableAnimal("Sparky", "Bull dog", "Dog");
        testAnimals[0].setId("1");
        testAnimals[1] = new AdoptableAnimal("Snowball", "Black", "Cat");
        testAnimals[1].setId("2");
        testAnimals[2] = new AdoptableAnimal("Apollo", "Parrot", "Bird");
        testAnimals[2].setId("3");

        when(mockObjectMapper.readValue(new File("doesn't_matter.txt"), AdoptableAnimal[].class)).thenReturn(testAnimals); 

        animalDAO = new AdoptableAnimalDAO("doesn't_matter.txt", mockObjectMapper);
    }

    @Test
    public void testGetAnimals() throws IOException {
        AdoptableAnimal[] animals = animalDAO.getAnimals(); 
        assertEquals(animals.length, testAnimals.length); 
        for(int i = 0; i < testAnimals.length; i++) {
            assertEquals(testAnimals[i].getId(), animals[i].getId()); 
        }
    }

    //Adoptable Animal create animal (animal)

    @Test 
    public void testCeateAnimal() throws IOException {
        AdoptableAnimal animal = new AdoptableAnimal("Test animal", "description", "species"); 
        String id = "animal4";
        animal.setId(id); 

        AdoptableAnimal result = assertDoesNotThrow(() -> animalDAO.createAnimal(animal), "Unexpected exception thrown"); 

        assertNotNull(result); 
        AdoptableAnimal actual = animalDAO.getAnimal(id); 
        assertEquals(animal.getId(), actual.getId()); 
        assertEquals(animal.getName(), actual.getName()); 
        assertEquals(animal.getDescription(), actual.getDescription()); 
        assertEquals(animal.getSpecies(), actual.getSpecies()); 
    }

    //create animal fail 
    /*
    @Test
    public void testCreateAnimalFail() throws IOException {
        AdoptableAnimal animal = null; 
        assertFalse(animalDAO.createAnimal(animal)); 
    }
    */

    //Animal update animal (animal) 

    @Test 
    public void testUpdateAnimal() throws IOException {
        AdoptableAnimal animal = new AdoptableAnimal("New Animal", "Updated Description", "Updated Species");
        animal.setId("2"); 

        AdoptableAnimal result = assertDoesNotThrow(() -> animalDAO.updateAnimal(animal), "Unexpected exception throws"); 

        assertNotNull(result); 

        AdoptableAnimal actual = animalDAO.getAnimal("2"); 
        assertEquals(animal, actual); 
    }

    @Test 
    public void testUpdateAnimalNotFound() throws IOException {
        AdoptableAnimal animal = new AdoptableAnimal("Uh oh doesn't exist", "Description", "Species"); 

    //     AdoptableAnimal result = assertDoesNotThrow(() -> animalDAO.updateAnimal(animal), "Unexpected exception thrown"); 

    //     assertNull(result); 
    // }

    @Test
    public void testGetAnimal() throws IOException {
        AdoptableAnimal animal = animalDAO.getAnimal("2"); 
        assertEquals(testAnimals[1].getId(), animal.getId()); 
        assertEquals(testAnimals[1], animal); 
    }

    @Test 
    public void testGetAnimalNotFound() throws IOException {
        AdoptableAnimal animal = animalDAO.getAnimal("Oh no it doesn't exist"); 
        assertNull(animal); 
    }

    @Test 
    public void testDeleteAnimal() throws IOException {
        boolean result = assertDoesNotThrow(() -> animalDAO.deleteAnimal("1"), "Unexpected exception thrown"); 

        assertTrue(result); 
        assertEquals(testAnimals.length - 1, animalDAO.getAnimals().length); 
    }

    @Test 
    public void testDeleteAnimalNotFound() throws IOException {
        boolean result = assertDoesNotThrow(() -> animalDAO.deleteAnimal("uh oh doesn't exist")); 
        assertFalse(result); 
        assertEquals(testAnimals.length, animalDAO.getAnimals().length); 

    }

    @Test 
    public void testAdoptAnimal() throws IOException {
        AdoptableAnimal animal = testAnimals[0]; 
        boolean result = assertDoesNotThrow(() -> animalDAO.adoptAnimal(animal), "Unexpected exception thrown"); 

        assertTrue(result); 
        assertEquals(true, testAnimals[0].getIsAdopted()); 
    }

    @Test
    public void testAdoptAnimalFail() throws IOException {
        AdoptableAnimal animal = new AdoptableAnimal("uh oh", "description", "species"); 
        boolean result = assertDoesNotThrow(() -> animalDAO.adoptAnimal(animal), "Unexpected exception thrown"); 

        assertFalse(result); 
    }

}

