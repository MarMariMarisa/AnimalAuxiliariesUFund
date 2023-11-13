package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.mockito.Mockito;

@Tag("persistence-tier")
public class HelperFileDAOTest {
    HelperFileDAO helperFileDAO;
    Helper[] testHelpers;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;
    NeedFileDAO mockNeedFileDAO;

    @BeforeEach
    public void setupHelperFileDAO() throws IOException{
        // Setup mock needs and helpers
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
        testHelpers[0] = new Helper("user1", "password");  
        testHelpers[1] = new Helper("user2", "password2");

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
    public void testCreateHelperExists() throws IOException{
        Helper helper = new Helper("example");
        Helper result = assertDoesNotThrow(() -> helperFileDAO.createHelper(helper), "Unexpected exception thrown");
        assertNotNull(result);
        int afterAddition = helperFileDAO.getHelpers().length;
        assertEquals(testHelpers.length+1, afterAddition);

        result = assertDoesNotThrow(() -> helperFileDAO.createHelper(helper), "Unexpected exception thrown");
        assertNull(result);
        assertEquals(afterAddition, helperFileDAO.getHelpers().length);
    }

    @Test
    public void testAddToBasket() throws IOException {
        when(mockNeedFileDAO.getNeed(testNeeds[0].getId())).thenReturn(testNeeds[0]);

        //assertTrue(testHelpers[0].addToFundingBasket(testNeeds[0]));
        Need need = helperFileDAO.addToBasket(testHelpers[0].getUsername(), testNeeds[0]);
        
        assertEquals(testNeeds[0].getId(), need.getId());     
    }

    @Test
    public void testAddToBasketHelperDoesNotExist() throws IOException {
        Need result = helperFileDAO.addToBasket("non_existing_user", testNeeds[0]);

        assertEquals(null, result);
    }

    @Test
    public void testAddToBasketNeedDoesNotExist() throws IOException {
        Need result = helperFileDAO.addToBasket("user1", null);

        assertEquals(null, result);
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
    public void testRemoveFromBasketHelperDoesNotExist() throws IOException {
        when(mockNeedFileDAO.getNeed(testNeeds[0].getId())).thenReturn(testNeeds[0]);
        assertTrue(testHelpers[0].addToFundingBasket(testNeeds[0]));
        Need result = helperFileDAO.removeFromBasket("doesnt-exist", testNeeds[0].getId());

        // Assert that the result is null since the helper doesn't exist
        assertEquals(null, result);
    }


    @Test
    public void testRemoveFromBasketNeedDoesNotExist() throws IOException {
        when(mockNeedFileDAO.getNeed("fakeNeed")).thenReturn(null);
        Need result = helperFileDAO.removeFromBasket(testHelpers[0].getUsername(), "fakeNeed");

        // Assert that the result is null since the need doesn't exist
        assertEquals(null, result);
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

    @Test
    public void testGetBasketNeedNoHelper() throws IOException{

        Need[] needs = helperFileDAO.getBasketNeeds("does not exist");

        assertEquals(null, needs);
    }

    @Test
    public void testAddToBasketWhenHelperExistsAndNeedNotInBasket() throws IOException {
        // Mocked data
        Helper helper = new Helper("user1");
        Need need = new Need("Item 1", "Description 1", "Category 1", 20, 5);

        // Stub the needFileDAO methods
        when(mockNeedFileDAO.updateNeed(Mockito.any())).thenReturn(null);

        // Initialize the helper with no basket needs
        helper.addToFundingBasket(new Need("Another Item", "Another Description", "Category 2", 10, 2));
        when(mockObjectMapper.readValue("file path", Helper[].class)).thenReturn(new Helper[]{helper});

        // Invoke
        Need result = helperFileDAO.addToBasket("user1", need);

        // Verify
        assertNotNull(result);
        // This is subtracted here because our front end actually handles the quantity decrementing
        assertEquals(4, result.getQuantity()-1);
    }

    @Test
    public void testRemoveFromBasketWhenNeedIsNotInNeedDAO() throws IOException {
        
        // Arrange
        String username = "testUser";
        String needID = "nonExistentNeedID";

        Helper helper = new Helper(username);
        Need mockNeedInBasket = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        mockNeedInBasket.setID("123"); // Set an ID
        helper.addToFundingBasket(mockNeedInBasket);
        helperFileDAO.createHelper(helper);
        helperFileDAO.addToBasket(username, mockNeedInBasket);

        // Configure needDao.getNeed to return null when looking for needID
        Mockito.when(mockNeedFileDAO.getNeed(needID)).thenReturn(null);

        // Act
        Need removedNeed = helperFileDAO.removeFromBasket(username, "123");

        // Assert
        assertNotNull(removedNeed);

        assertEquals(removedNeed.getId(), mockNeedInBasket.getId());
    }

    @Test
    public void testCheckCredentials() {
        // Test with valid credentials
        assertTrue(helperFileDAO.checkCredentials("user1", "password"));
        assertTrue(helperFileDAO.checkCredentials("user2", "password2"));
        // Test with valid username but incorrect password
        assertFalse(helperFileDAO.checkCredentials("user1", "incorrectPassword"));

        // Test with non-existing username
        assertFalse(helperFileDAO.checkCredentials("nonExistingUser", "password"));

        // Test with null username and password
        assertFalse(helperFileDAO.checkCredentials(null, null));
    }

    @Test 
    public void testCheckout() throws IOException{
        when(mockNeedFileDAO.getNeed(testNeeds[0].getId())).thenReturn(testNeeds[0]);

        helperFileDAO.addToBasket(testHelpers[0].getUsername(), testNeeds[0]);
        assertTrue(helperFileDAO.getBasketNeeds(testHelpers[0].getUsername()).length == 1);  

        assertTrue(helperFileDAO.checkout(testHelpers[0].getUsername()));
        assertTrue(helperFileDAO.getBasketNeeds(testHelpers[0].getUsername()).length == 0);  
    }

    @Test 
    public void testCheckoutHelperDoesntExist() throws IOException{
        assertFalse(helperFileDAO.checkout("does not exist"));
     }
}
