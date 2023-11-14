package com.ufund.api.ufundapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.HelperFileDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class HelperControllerTest {
    private HelperController helperController;
    @Mock
    private HelperFileDAO mockHelperDAO;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setupHelperController() throws IOException {
        mockHelperDAO = mock(HelperFileDAO.class);
        helperController = new HelperController(mockHelperDAO);
    }

    @Test
    public void testGetHelpersBasket() throws IOException {
        // Setup
        String username = "user1";
        Need[] basketNeeds = new Need[2];
        basketNeeds[0] = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        basketNeeds[1] = new Need("Item 2", "Description 2", "Category 2", 30, 7);
        when(mockHelperDAO.getBasketNeeds(username)).thenReturn(basketNeeds);

        // Invoke
        ResponseEntity<Need[]> response = helperController.getHelpersBasket(username);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(basketNeeds, response.getBody());
    }

    @Test
    public void testAddToBasket() throws IOException {
        // Setup
        String username = "user1";
        Need need = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        String needJson = objectMapper.writeValueAsString(need);
        when(mockHelperDAO.addToBasket(username, need)).thenReturn(need);

        // Invoke
        ResponseEntity<Need> response = helperController.addToBasket(username, needJson);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(need, response.getBody());
    }

    @Test
    public void testAddToBasketConflict() throws IOException {
        // Setup
        String username = "user1";
        Need need = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        String needJson = objectMapper.writeValueAsString(need);
        when(mockHelperDAO.addToBasket(username, need)).thenReturn(null);

        // Invoke
        ResponseEntity<Need> response = helperController.addToBasket(username, needJson);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateHelper() throws IOException {
        // Setup
        String helperJson = "{\"id\":\"1\",\"username\":\"user1\",\"basket\":{\"needs\":[]}}";
        Helper helper = new Helper("user1");

        // Mock the behavior of the DAO
        when(mockHelperDAO.createHelper(any(Helper.class))).thenReturn(helper);
        when(mockHelperDAO.getHelpers()).thenReturn(new Helper[0]);

        // Invoke the createHelper method
        ResponseEntity<Helper> response = helperController.createHelper(helperJson);

        // Analyze the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Helper h = response.getBody();
        if(h != null){
            String username = h.getUsername();
            if(username != null){
                assertEquals("user1", username);
            }
            else{
                assertTrue(false);
            }
        }
        else{
            assertTrue(false);
        }
        
        
    }

    @Test
    public void testCreateHelperConflict() throws IOException {
        // Setup
        //String helperJson = "{\"username\":\"user1\"}";
        String helperJson = "{\"id\":\"1\",\"username\":\"user1\",\"basket\":{\"needs\":[]}}";
        //Helper helper = objectMapper.readValue(helperJson, Helper.class);
        when(mockHelperDAO.getHelpers()).thenReturn(new Helper[] { new Helper("user1") });

        // Invoke
        ResponseEntity<Helper> response = helperController.createHelper(helperJson);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testRemoveFromBasket() throws IOException {
        // Setup
        String username = "user1";
        String needID = "need1";
        Need removedNeed = new Need("Removed Need", "Description", "Category", 10, 2);
        when(mockHelperDAO.removeFromBasket(username, needID)).thenReturn(removedNeed);

        // Invoke
        ResponseEntity<Need> response = helperController.removeFromBasket(username, needID);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(removedNeed, response.getBody());
    }

    @Test
    public void testRemoveFromBasketNeedNotExists() throws IOException {
        // Setup
        String username = "user1";
        String needID = "nonexistent";
        when(mockHelperDAO.removeFromBasket(username, needID)).thenReturn(null);

        // Invoke
        ResponseEntity<Need> response = helperController.removeFromBasket(username, needID);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
    @Test
    public void testGetHelpersBasketException() throws IOException {
        when(mockHelperDAO.getBasketNeeds("user1")).thenThrow(new IOException("Simulated Exception"));

        ResponseEntity<Need[]> response = helperController.getHelpersBasket("user1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAddToBasketException() throws IOException {
        when(mockHelperDAO.addToBasket(eq("user1"), any(Need.class))).thenThrow(new IOException("Simulated Exception"));

        ResponseEntity<Need> response = helperController.addToBasket("user1", "{}");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
}

    @Test
    public void testCreateHelperException() throws IOException {
        when(mockHelperDAO.createHelper(any(Helper.class))).thenThrow(new IOException("Simulated Exception"));

        ResponseEntity<Helper> response = helperController.createHelper("{}");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testRemoveFromBasketException() throws IOException {
        when(mockHelperDAO.removeFromBasket("user1", "need1")).thenThrow(new IOException("Simulated Exception"));

        ResponseEntity<Need> response = helperController.removeFromBasket("user1", "need1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

     @Test
    public void testCheckoutSuccess() throws IOException {
        // Arrange
        String username = "testUser";
        when(mockHelperDAO.checkout(username)).thenReturn(true);

        // Act
        ResponseEntity<Boolean> responseEntity = helperController.checkout(username);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Boolean b = responseEntity.getBody();
        if(b != null){
            assertTrue(b);
        }
        
    }

    @Test
    public void testCheckoutIOException() throws IOException {
        // Arrange
        String username = "testUser";
        when(mockHelperDAO.checkout(username)).thenThrow(new IOException("Simulated IOException"));

        // Act
        ResponseEntity<Boolean> responseEntity = helperController.checkout(username);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
    @Test
    public void testAdoptAnimal() throws IOException {
        String animalId = "123";
        when(mockHelperDAO.adoptAnimal(animalId)).thenReturn(true);

        ResponseEntity<Boolean> response = helperController.adoptAnimal(animalId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody());
    }

    @Test
    public void testAdoptAnimalFail() throws IOException {
        String animalId = "456";
        when(mockHelperDAO.adoptAnimal(animalId)).thenReturn(false);

        ResponseEntity<Boolean> response = helperController.adoptAnimal(animalId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody());
    }

    @Test
    public void testAdoptAnimalIOE() throws IOException {
        String animalId = "789";
        when(mockHelperDAO.adoptAnimal(animalId)).thenThrow(new IOException("Simulated IOException"));

        ResponseEntity<Boolean> response = helperController.adoptAnimal(animalId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
