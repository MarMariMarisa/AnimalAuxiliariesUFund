package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.HelperFileDAO;

public class HelperControllerTest {
    private HelperController helperController;
    private HelperFileDAO mockHelperDAO;

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
    public void testGetHelpersBasketNotFound() throws IOException {
        // Setup
        String username = "user1";
        when(mockHelperDAO.getBasketNeeds(username)).thenThrow(new IOException());

        // Invoke
        ResponseEntity<Need[]> response = helperController.getHelpersBasket(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAddToBasket() throws IOException {
        // Setup
        String username = "user1";
        Need need = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        when(mockHelperDAO.addToBasket(username, need)).thenReturn(need);

        // Invoke
        ResponseEntity<Need> response = helperController.addToBasket(username, need);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(need, response.getBody());
    }

    @Test
    public void testAddToBasketConflict() throws IOException {
        // Setup
        String username = "user1";
        Need need = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        when(mockHelperDAO.addToBasket(username, need)).thenReturn(null);

        // Invoke
        ResponseEntity<Need> response = helperController.addToBasket(username, need);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testAddToBasketHandleException() throws IOException {
        // Setup
        String username = "user1";
        Need need = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        when(mockHelperDAO.addToBasket(username, need)).thenThrow(new IOException());

        // Invoke
        ResponseEntity<Need> response = helperController.addToBasket(username, need);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateHelper() throws IOException {
        // Setup
        Helper testHelper = new Helper("user1");
        when(mockHelperDAO.createHelper(any(Helper.class))).thenReturn(testHelper);

        // Invoke 
        ResponseEntity<Helper> response = helperController.createHelper(testHelper);

        // Analyze 
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testHelper, response.getBody());
    }

    @Test
    public void testCreateHelperConflict() throws IOException {
        // Setup
        Helper helper = new Helper("user1");
        when(mockHelperDAO.getHelpers()).thenReturn(new Helper[] { new Helper("user2") });

        // Invoke
        ResponseEntity<Helper> response = helperController.createHelper(helper);

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
        assertNull(response.getBody());
    }

    @Test
    public void testRemoveFromBasketHandleException() throws IOException {
        // Setup
        String username = "user1";
        String needID = "need1";
        when(mockHelperDAO.removeFromBasket(username, needID)).thenThrow(new IOException("Test Exception"));

        // Invoke
        ResponseEntity<Need> response = helperController.removeFromBasket(username, needID);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
