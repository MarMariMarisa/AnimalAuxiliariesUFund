package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

public class CupboardControllerTest {
    private CupboardController cupboardController;
    private NeedFileDAO mockNeedDAO;
    private Cupboard cupboard;
    
    @BeforeEach
    public void setupCupboardController() throws IOException{
        mockNeedDAO = mock(NeedFileDAO.class);
        cupboardController = new CupboardController(cupboard, mockNeedDAO);
    }

    @Test
    public void testGetEntireCupboard() throws IOException{
        // Setup

        // Invoke
        

        // Analyze
    }
    @Test
    public void testSearchOnName(){
        // Setup

        // Invoke

        // Analyze
    }
    @Test
    public void testUpdateNeed(){
        // Setup

        // Invoke

        // Analyze
    }
    @Test
    public void testDeleteNeed(){
        // Setup

        // Invoke

        // Analyze
    }
    @Test
    public void testCreateNeed(){
        // Setup

        // Invoke

        // Analyze
    }
}
