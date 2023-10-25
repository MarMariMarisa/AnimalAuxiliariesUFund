package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("persistence-tier")
public class HelperFileDAOTest {
    HelperFileDAO helperFileDAO;
    Helper[] testHelpers;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;
    NeedFileDAO mockNeedFileDAO;

    @BeforeEach
    public void setupHelperFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        mockNeedFileDAO = mock(NeedFileDAO.class);
        testNeeds = new Need[3];
        testNeeds[0] = new Need("dog leash", "a dog leash", "leashes", 25, 5);
        testNeeds[0].setID("need1");
        testNeeds[1] = new Need("Second Need", "Description 2", "Category 2", 30, 7);
        testNeeds[1].setID("need2");
        testNeeds[2] = new Need("Third Need", "Description 3", "Category 3", 15, 3);
        testNeeds[2].setID("need3");


        testHelpers = new Helper[2];
        testHelpers[0] = new Helper("user1");  
        testHelpers[1] = new Helper("user2");

        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"), Helper[].class))
            .thenReturn(testHelpers);
        helperFileDAO = new HelperFileDAO("doesnt_matter.txt", mockObjectMapper, mockNeedFileDAO);
    }

    @Test
    public void testGetHelpers() throws IOException {
        Helper[] helpers = helperFileDAO.getHelpers();
        assertEquals(testHelpers.length, helpers.length);
    }

    @Test
    public void testCreateHelper() throws IOException{
        Helper helper = new Helper("example");

        Helper result = assertDoesNotThrow(() -> helperFileDAO.createHelper(helper), "Unexpected exception thrown");
        assertNotNull(result);
        assertEquals(testHelpers.length+1, helperFileDAO.getHelpers().length);
    }

    @Test
    public void testAddToBasket() throws IOException {
        when(mockNeedFileDAO.getNeed(testNeeds[0].getId())).thenReturn(testNeeds[0]);

        // Perform the test
        assertTrue(testHelpers[0].addToFundingBasket(testNeeds[0]));
        Need need = helperFileDAO.addToBasket(testHelpers[0].getUsername(), testNeeds[0]);

        // Assert the results
        assertEquals(testNeeds[0].getId(), need.getId());
        
    }

    @Test
    public void testremoveFromBasket() throws IOException {
        when(mockNeedFileDAO.getNeed(testNeeds[0].getId())).thenReturn(testNeeds[0]);

        // Perform the test
        testHelpers[0].addToFundingBasket(testNeeds[0]);
        assertTrue(testHelpers[0].removeFromFundingBasket(testNeeds[0]));
        testHelpers[0].addToFundingBasket(testNeeds[0]);
        Need need = helperFileDAO.removeFromBasket(testHelpers[0].getUsername(), testNeeds[0].getId());

        // Assert the results
        assertEquals(testNeeds[0].getId(), need.getId());
    }

    @Test
    public void testGetBasketNeed() throws IOException{
        testHelpers[0].addToFundingBasket(testNeeds[0]);
        testHelpers[0].addToFundingBasket(testNeeds[1]);

        Need[] needs = helperFileDAO.getBasketNeeds(testHelpers[0].getUsername());

        int basketLength = 2;
        for(int i = 0; i < basketLength; i++){
            assertEquals(testNeeds[i], needs[i]);
        }
    }
}
