package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.logging.Logger;
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
    private Logger mockLogger;

    @BeforeEach
    public void setupHelperController() throws IOException {
        mockHelperDAO = mock(HelperFileDAO.class);
        mockLogger = mock(Logger.class);
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
        assertEquals(need.getId(), response.getBody().getId());
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
    public void testCreateHelper() {
        // Setup
        Helper helper = new Helper("user1");
        when(mockHelperDAO.getHelpers()).thenReturn(new Helper[0]);

        // Invoke
        ResponseEntity<Helper> response = helperController.createHelper(helper);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(helper, response.getBody());
    }

    @Test
    public void testCreateHelperConflict() {
        // Setup
        Helper helper = new Helper("user1");
        when(mockHelperDAO.getHelpers()).thenReturn(new Helper[] { new Helper("user2") });

        // Invoke
        ResponseEntity<Helper> response = helperController.createHelper(helper);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
