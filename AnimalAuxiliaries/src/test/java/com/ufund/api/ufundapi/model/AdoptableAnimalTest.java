package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testEqualsSameObject() {
        AdoptableAnimal animal = new AdoptableAnimal();
        assertTrue(animal.equals(animal));
    }

    @Test
    public void testEqualsNullObject() {
        AdoptableAnimal animal = new AdoptableAnimal();
        assertFalse(animal.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        AdoptableAnimal animal = new AdoptableAnimal();
        Object otherObject = new Object();
        assertFalse(animal.equals(otherObject));
    }

    @Test
    public void testEqualsSameId() {
        AdoptableAnimal animal1 = new AdoptableAnimal();
        AdoptableAnimal animal2 = new AdoptableAnimal();
        animal1.setId("id");
        animal2.setId("id");

        assertTrue(animal1.equals(animal2));
    }

    @Test
    public void testEqualsDifferentId() {
        AdoptableAnimal animal1 = new AdoptableAnimal();
        AdoptableAnimal animal2 = new AdoptableAnimal();

        assertFalse(animal1.equals(animal2));
    }
    
    @Test
    public void testSetImageUrl() {
        AdoptableAnimal animal1 = new AdoptableAnimal();
        String imageUrl = "https://example.com/image.jpg";

        animal1.setImageUrl(imageUrl);

        assertEquals(imageUrl, animal1.getImageUrl());
    }

    @Test
    public void testHashCode(){
        AdoptableAnimal animal1 = new AdoptableAnimal();
        int code = animal1.hashCode();
        assertEquals(animal1.getId().hashCode(), code);
    }
}
