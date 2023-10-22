//TODO: Convert to CupboardControllerTest @Tyler @Tszfai

package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Cupboard;

public class CupboardControllerTest {
    private CupboardController cupboardController;
    private NeedFileDAO mockNeedDAO;
    private Cupboard testCupboard;

    @BeforeEach
    public void setupNeedController() throws IOException{
            testCupboard = new Cupboard();
            mockNeedDAO = mock(NeedFileDAO.class);
            cupboardController = new CupboardController(testCupboard, mockNeedDAO);
    }
    @Test
    public void testGetNeed() throws IOException {  
        // Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        need.setID("5");
        when(mockNeedDAO.getNeed("5")).thenReturn(need);
        testCupboard.addNeed(need);
        // Invoke
        ResponseEntity<Need> response = cupboardController.getNeed("5");
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void testGetNeedNotFound() throws Exception { 
        // Setup
        String needID = "1";
        when(mockNeedDAO.getNeed(needID)).thenReturn(null);

        // Invoke
        ResponseEntity<List<Need>> response = cupboardController.getEntireCupboard();

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetEntireCupboard() throws IOException {  
        // Setup
        List<Need> needs = new ArrayList<>();
        needs.add(new Need());
        needs.add(new Need());
        when(mockNeedDAO.getNeeds()).thenReturn(needs.toArray(new Need[2]));
        testCupboard.addNeed(new Need());
        testCupboard.addNeed(new Need());
        // Invoke
        ResponseEntity<List<Need>> response = cupboardController.getEntireCupboard();
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(response.getBody().size(), needs.size());
    }
    @Test
    public void testGetEntireCupboardNotFound() throws IOException {  
        // Setup
        List<Need> needs = new ArrayList<>();
        when(mockNeedDAO.getNeeds()).thenReturn(needs.toArray(new Need[2]));
        // Invoke
        ResponseEntity<List<Need>> response = cupboardController.getEntireCupboard();
        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());;
    }

    @Test
    public void testSearchCupboard() throws IOException { 
                // Setup
                String searchString = "Dog";
                int expectedSize = 1;
                List<Need> needs = new ArrayList<>();
                Need need = new Need("dog leash","a dog leash","leashes",25,5);
                needs.add(need);
                testCupboard.addNeed(need);
                need = new Need("cat leash","a cat leash","leashes",25,5);
                needs.add(need);
                testCupboard.addNeed(need);
                // Invoke
                ResponseEntity<List<Need>> response = cupboardController.searchOnName(searchString);
        
                // Assert
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(response.getBody().size(), expectedSize);
    }


    @Test
    public void testUpdateNeed() throws IOException {
        //Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        testCupboard.addNeed(need);

        //Invoke
        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        
        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateNeedNotFound() throws IOException {
        //Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);

        //Invoke
        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testDeleteNeed() throws IOException{
        // Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        need.setID("5");
        when(mockNeedDAO.getNeed("5")).thenReturn(need);
        testCupboard.addNeed(need);
        // Invoke
        ResponseEntity<HttpStatus> response = cupboardController.deleteNeed("5");
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteNeedNotFound() throws IOException{
        //Setup

        //Invoke
        ResponseEntity<HttpStatus> response = cupboardController.deleteNeed("0");

        //Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testCreateNeed() throws IOException {
        //Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        need.setID("5");

        //Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);
        response.getBody();
        //Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test public void testCreateNeedConflict() throws IOException{
        //Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        need.setID("5");
        testCupboard.addNeed(need);


        //Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);
        response.getBody();
        //Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}
