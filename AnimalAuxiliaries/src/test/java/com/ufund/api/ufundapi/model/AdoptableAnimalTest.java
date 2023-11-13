package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Tyler Combs (tec8354)
 */
public class AdoptableAnimalTest {

    @Test
    public void testConstructor(){
        //Setup
        String expectedName = "Animal";
        String expectedDescription = "";
        String expectedSpecies = "Dog";
        String expectedImageUrl = "";

        //Invoke
        AdoptableAnimal animal = new AdoptableAnimal();

        //Analyze
        assertEquals(expectedName, animal.getName());
        assertEquals(expectedDescription, animal.getDescription());
        assertEquals(expectedSpecies, animal.getSpecies());
        assertEquals(expectedImageUrl, animal.getImageUrl());

    }

    @Test
    public void testEqulas(){
        //Setup
        AdoptableAnimal animal = new AdoptableAnimal();
        AdoptableAnimal animal2 = animal;

        //Invoke

        //Analyze
        assertEquals(animal, animal2);
    }

    @Test
    public void testId(){
        //Setup
        String expectedId = "1";
        AdoptableAnimal animal = new AdoptableAnimal();

        //Invoke
        animal.setId("1");

        //Analyze
        assertEquals(expectedId, animal.getId());

    }

    @Test
    public void testName(){
        //Setup
        String expectedName = "Snowball";
        AdoptableAnimal animal = new AdoptableAnimal();

        //Invoke
        animal.setName("Snowball");

        //Analyze
        assertEquals(expectedName, animal.getName());

    }

    @Test
    public void testDescription(){
        //Setup
        String expectedDescription = "New Animal";
        AdoptableAnimal animal = new AdoptableAnimal();

        //Invoke
        animal.setDescription("New Animal");

        //Analyze
        assertEquals(expectedDescription, animal.getDescription());

    }

    @Test
    public void testSpecies(){
        //Setup
        String expectedSpecies = "Cat";
        AdoptableAnimal animal = new AdoptableAnimal();

        //Invoke
        animal.setSpecies("Cat");

        //Analyze
        assertEquals(expectedSpecies, animal.getSpecies());

    }
    
}
