package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class HelperTest {

    private final String USERNAME = "helper"; 

    private Helper helper; 

    @BeforeEach 
    public void setup() {
        helper = new Helper(USERNAME); 
    }

    @Test
    public void testGetBasket() {
        Need[] needs = {new Need("need 1", "", "", 1.00f, 1), 
                        new Need("need 2", "", "", 1.00f, 1), 
                        new Need("need 3", "", "", 1.00f, 1)}; 

        for(int i = 0; i < needs.length; i++) {
            helper.addToFundingBasket(needs[i]);
        }

        assertArrayEquals(needs, helper.getBasketNeeds()); 
    }

    @Test
    public void testGetBasketEmpty() {
        assertEquals(0, helper.getBasketNeeds().length); 
    }

    @Test
    public void testAddToBasket() {
        Need need = new Need("need 4", "", "", 1.00f, 1); 

        assertTrue(helper.addToFundingBasket(need)); 

        assertEquals(1, helper.getBasketNeeds().length);      

    }

    @Test 
    public void testAddToBasketNoNeed() {
        assertFalse(helper.addToFundingBasket(null)); 
    }

    @Test
    public void testRemoveFromBasket() {
        Need[] needs = {new Need("need 1", "", "", 1.00f, 1), 
                        new Need("need 2", "", "", 1.00f, 1), 
                        new Need("need 3", "", "", 1.00f, 1)};

        for(int i = 0; i < needs.length; i++) {
            helper.addToFundingBasket(needs[i]); 
        }

        assertTrue(helper.removeFromFundingBasket(needs[1])); 

        assertEquals(needs.length - 1, helper.getBasketNeeds().length); 
        assertFalse(helper.getBasketNeeds().equals(needs)); 
        //TO DO I think there needs to be some extra confirmation here 
    }

    @Test
    public void testRemoveFromBasketNotPresent() {
        Need[] needs = {new Need("need 1", "", "", 1.00f, 1), 
                        new Need("need 2", "", "", 1.00f, 1), 
                        new Need("need 3", "", "", 1.00f, 1)};
        
        Need need = new Need("need 4", "", "", 1.00f, 1); 

        for(int i = 0; i < needs.length; i++) {
            helper.addToFundingBasket(needs[i]); 
        }

        assertFalse(helper.removeFromFundingBasket(need)); 
    }

    @Test 
    public void testRemoveFromBasketNoNeed() {
        assertFalse(helper.removeFromFundingBasket(null)); 
    }

    @Test
    public void testCheckout(){
        Need[] needs = {new Need("need 1", "", "", 1.00f, 1), 
                        new Need("need 2", "", "", 1.00f, 1), 
                        new Need("need 3", "", "", 1.00f, 1)};
        
        Need need = new Need("need 4", "", "", 1.00f, 1); 

        for(int i = 0; i < needs.length; i++) {
            helper.addToFundingBasket(needs[i]); 
        }

        assertTrue(helper.getBasketNeeds().length > 0);
        helper.checkout();

        assertTrue(helper.getBasketNeeds().length == 0);
    }

    //helper functions:
    //add to funding basket 
    // remove from funding basket 
    
}
