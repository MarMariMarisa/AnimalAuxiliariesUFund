package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

public class CupboardControllerTest {
    private CupboardController cupboardController;
    private NeedFileDAO mockNeedDAO;

    @BeforeEach
    public void setupNeedController() throws IOException{
            //testCupboard = new Cupboard();
            mockNeedDAO = mock(NeedFileDAO.class);
            cupboardController = new CupboardController(mockNeedDAO);
    }
    @Test
    public void testGetNeed() throws IOException {  
        // Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        need.setID("5");
        when(mockNeedDAO.getNeed("5")).thenReturn(need);
        //testCupboard.addNeed(need);
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
        ResponseEntity<Need> response = cupboardController.getNeed(needID);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetEntireCupboard() throws IOException {  
        // Setup
        Need[] needs = new Need[2];
        needs[0] = new Need("Second Need", "Description 2", "Category 2", 30, 7);
        needs[1] = new Need("dog leash", "a dog leash", "leashes", 25, 5);
        when(mockNeedDAO.getNeeds()).thenReturn(needs);
  
        // Invoke
        ResponseEntity<Need[]> response = cupboardController.getEntireCupboard();
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(response.getBody(), needs);
    }
    @Test
    public void testGetEntireCupboardHandleException() throws IOException {  
        doThrow(new IOException()).when(mockNeedDAO).getNeeds();

        // Invoke
        ResponseEntity<Need[]> response = cupboardController.getEntireCupboard();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchCupboard() throws IOException { 
        // Setup
        Need[] needs = new Need[2];
        needs[0] = new Need("Second Need", "Description 2", "Category 2", 30, 7);
        needs[1] = new Need("Third Need", "a dog leash", "leashes", 25, 5);
        String search = "ee";
        when(mockNeedDAO.findNeeds(search)).thenReturn(needs);
        // Invoke
        ResponseEntity<Need[]> response = cupboardController.searchOnName(search);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(needs, response.getBody());
    }


    @Test
    public void testUpdateNeed() throws IOException {
        //Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        when(mockNeedDAO.updateNeed(need)).thenReturn(need);

        //Invoke
        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        need.setName("cat leash");
        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    @Test
    public void testUpdateNeedNotFound() throws IOException {
        //Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        when(mockNeedDAO.updateNeed(need)).thenReturn(null);

        //Invoke
        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testDeleteNeed() throws IOException{
        // Setup
        when(mockNeedDAO.deleteNeed("10")).thenReturn(true);

        // Invoke
        ResponseEntity<HttpStatus> response = cupboardController.deleteNeed("10");
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteNeedNotFound() throws IOException{
        //Setup
        when(mockNeedDAO.deleteNeed("0")).thenReturn(false);
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
        when(mockNeedDAO.createNeed(need)).thenReturn(need);
        //Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);
       
        //Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    @Test public void testCreateNeedConflict() throws IOException{
        //Setup
        Need need = new Need("dog leash","a dog leash","leashes",25,5);
        need.setID("5");
        when(mockNeedDAO.createNeed(need)).thenReturn(null);


        //Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);
        //Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }


    @Test
    public void testUpdateNeedHandleException() throws IOException {
        // Setup
        Need need = new Need("dog leash", "a dog leash", "leashes", 25, 5);
        doThrow(new IOException()).when(mockNeedDAO).updateNeed(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.updateNeed(need);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteNeedHandleException() throws IOException {
        // Setup
        doThrow(new IOException()).when(mockNeedDAO).deleteNeed("10");

        // Invoke
        ResponseEntity<HttpStatus> response = cupboardController.deleteNeed("10");

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateNeedHandleException() throws IOException {
        // Setup
        Need need = new Need("dog leash", "a dog leash", "leashes", 25, 5);
        doThrow(new IOException()).when(mockNeedDAO).createNeed(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testSearchCupboardHandleException() throws IOException {
        // Setup
        String search = "ee";
        doThrow(new IOException()).when(mockNeedDAO).findNeeds(search);

        // Invoke
        ResponseEntity<Need[]> response = cupboardController.searchOnName(search);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


}   
