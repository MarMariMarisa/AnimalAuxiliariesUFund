package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tyler Combs (tec8354)
 */
public class AdoptionCupboardTest {
    AdoptionCupboard adoptioncupboard;
    AdoptableAnimal[] testAnimals;

    private static final boolean ADOPTED = true; 
    private static final boolean NOT_ADOPTED = false; 
    @BeforeEach
    public void setUp(){
        adoptioncupboard = new AdoptionCupboard();

        testAnimals = new AdoptableAnimal[3];

        testAnimals[0] = new AdoptableAnimal("Sparky", "Bull dog", "Dog");
        testAnimals[0].setId("1");
        testAnimals[1] = new AdoptableAnimal("Snowball", "Black", "Cat");
        testAnimals[1].setId("2");
        testAnimals[2] = new AdoptableAnimal("Apollo", "Parrot", "Bird");
        testAnimals[2].setId("3");

        for(int i = 0; i < testAnimals.length; i++){
            adoptioncupboard.addAnimal(testAnimals[i]);
        }

    }
    
    @Test
    public void testConstructor(){
        //Setup

        //Invoke

        //Analyze
        assertNotNull(adoptioncupboard, "Adoption Cupboard is null");
        assertNotNull(adoptioncupboard.getAnimals(), "Animals is null");

    }

    @Test
    public void testAdoptAnimal(){
        //Setup

        //Invoke
        adoptioncupboard.adoptAnimal(testAnimals[0]);

        //Analyze
        assertEquals(ADOPTED, testAnimals[0].getIsAdopted());


    }

    //if the animal object is null then the getId call in adoptAnimal throws a null pointer 
    //the animal id cannot be set to null because that value is final 
    
    @Test 
    public void testAdoptableAnimalNull() {
        AdoptableAnimal testAnimal = new AdoptableAnimal(); 
        testAnimal.setId(null); 
        adoptioncupboard.adoptAnimal(testAnimal); 
        int i = 0; 
        for(AdoptableAnimal animal : testAnimals) {
            assertEquals(animal, adoptioncupboard.getAnimals().get(i));
            i++; 
        }
    }
    

    @Test 
    public void testAdoptAnimalIdNotFound() {
        AdoptableAnimal testAnimal = new AdoptableAnimal();
        adoptioncupboard.adoptAnimal(testAnimal); 
        int i = 0; 
        for(AdoptableAnimal animal : testAnimals) {
            assertEquals(animal, adoptioncupboard.getAnimals().get(i));
            i++; 
        }
    }
    
    @Test
    public void testGetAdoptableAnimalOnSpecies(){
        //Setup
        AdoptableAnimal expectedAnimal = testAnimals[1];

        //Invoke
        List<AdoptableAnimal> result = adoptioncupboard.getAdoptableAnimalOnSpecies("Cat");

        //Analyze
        assertEquals(expectedAnimal, result.get(0));


    }

    @Test
    public void testGetAdoptableAnimalOnSpeciesNull() {
        ArrayList<AdoptableAnimal> list = new ArrayList<AdoptableAnimal>() {};
        assertEquals(list, adoptioncupboard.getAdoptableAnimalOnSpecies(null)); 
    }

    @Test 
    public void testGetAnimals() {
        ArrayList<AdoptableAnimal> animals = (ArrayList<AdoptableAnimal>) adoptioncupboard.getAnimals(); 
        int i = 0; 
        for(AdoptableAnimal animal : animals) {
            assertEquals(testAnimals[i], animal); 
            i++; 
        }
    }

    @Test 
    public void testGetAdoptableAnimalOnId(){
        //Setup
        AdoptableAnimal expectedAnimal = testAnimals[1];

        //Invoke
        List<AdoptableAnimal> result = adoptioncupboard.getAdoptableAnimalOnSpecies("Cat");

        //Analyze
        assertEquals(expectedAnimal, result.get(0));

    }

    @Test
    public void testGetAdoptableAnimalOnIdNull() {
        assertNull(adoptioncupboard.getAdoptableAnimalOnId(null));
    }

    @Test
    public void testAddAnimal(){
        //Setup
        AdoptableAnimal newAnimal = new AdoptableAnimal();

        //Invoke
        boolean result = adoptioncupboard.addAnimal(newAnimal);

        //Analyze
        assertEquals(true, result);

    }

    @Test
    public void testAddAnimalNull() {
        adoptioncupboard.addAnimal(null);
        int i = 0;  
        for(AdoptableAnimal animal : testAnimals) {
            assertEquals(animal, adoptioncupboard.getAnimals().get(i)); 
            i++; 
        }
    }

    @Test
    public void testUpdateAnimal(){
        //Setup
        AdoptableAnimal updatedAnimal = testAnimals[2];
        updatedAnimal.setName("Donald");

        //Invoke
        boolean result = adoptioncupboard.updateAnimal(updatedAnimal);
        

        //Analyze
        assertEquals(true, result);

    }

    @Test 
    public void testUpdateAnimalNotFound() {
        AdoptableAnimal testAnimal = new AdoptableAnimal(); 
        adoptioncupboard.updateAnimal(testAnimal); 
        int i = 0;  
        for(AdoptableAnimal animal : testAnimals) {
            assertEquals(animal, adoptioncupboard.getAnimals().get(i)); 
            i++; 
        }
    }

    @Test
    public void testDeleteAnimal(){
        //Setup

        //Invoke
        boolean result = adoptioncupboard.deleteAnimal("1");

        //Analyze
        assertEquals(true, result);

    }

    @Test 
    public void testDeleteAnimalNull() {
        adoptioncupboard.deleteAnimal(null); 
        int i = 0;  
        for(AdoptableAnimal animal : testAnimals) {
            assertEquals(animal, adoptioncupboard.getAnimals().get(i)); 
            i++; 
        }
    }
}
