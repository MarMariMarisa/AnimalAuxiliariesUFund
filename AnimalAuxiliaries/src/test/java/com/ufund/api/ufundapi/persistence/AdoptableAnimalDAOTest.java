package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.AdoptableAnimal;
import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;

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

        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"), Need[].class))
            .thenReturn(testAnimals);
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"), float.class))
            .thenReturn((float) 0.0);    
    }
}
