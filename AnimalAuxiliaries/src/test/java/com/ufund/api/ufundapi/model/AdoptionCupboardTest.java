package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tyler Combs (tec8354)
 */
public class AdoptionCupboardTest {
    AdoptionCupboard adoptioncupboard;
    AdoptableAnimal[] testAnimals;

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
        assertNotNull(adoptioncupboard.getCurrentAnimals(), "Current Animals is null");
        assertNotNull(adoptioncupboard.getAdoptedAnimals(), "Adopted Animals is not null");

    }

    @Test
    public void testAdoptAnimal(){
        //Setup
        int expectedSize = 1;

        //Invoke
        adoptioncupboard.adoptAnimal(testAnimals[0]);

        //Analyze
        assertEquals(adoptioncupboard.getAdoptedAnimals().size(), expectedSize);


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
    public void testGetAdoptableAnimalOnId(){
        //Setup
        AdoptableAnimal expectedAnimal = testAnimals[1];

        //Invoke
        List<AdoptableAnimal> result = adoptioncupboard.getAdoptableAnimalOnSpecies("Cat");

        //Analyze
        assertEquals(expectedAnimal, result.get(0));

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
    public void testDeleteAnimal(){
        //Setup

        //Invoke
        boolean result = adoptioncupboard.deleteAnimal("1");

        //Analyze
        assertEquals(true, result);

    }
}
