package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FundingBasketTest {
    private FundingBasket fundingBasket;

    @BeforeEach
    public void setUp() {
        fundingBasket = new FundingBasket();
    }

    @Test
    public void testGetBasketEmpty() {
        assertTrue(fundingBasket.getBasket().isEmpty());
    }

    @Test
    public void testAddToBasket() {
        Need need = new Need("Test Need", "Description", "Category", 100, 5);
        assertTrue(fundingBasket.addToBasket(need));
        assertTrue(fundingBasket.getBasket().contains(need));
    }

    @Test
    public void testAddToBasketNullNeed() {
        Need need = null;
        assertFalse(fundingBasket.addToBasket(need));
        assertTrue(fundingBasket.getBasket().isEmpty());
    }

    @Test
    public void testAddToBasketFundedNeed() {
        // Quantity = 0, funded = 0, so it is fully funded need
        Need fundedNeed = new Need("Funded Need", "Description", "Category", 100, 0);
        assertFalse(fundingBasket.addToBasket(fundedNeed));
        assertTrue(fundingBasket.getBasket().isEmpty());
    }

    @Test
    public void testRemoveFromBasket() {
        Need need = new Need("Test Need", "Description", "Category", 100, 5);
        fundingBasket.addToBasket(need);
        assertTrue(fundingBasket.removeFromBasket(need));
        assertFalse(fundingBasket.getBasket().contains(need));
    }

    @Test
    public void testRemoveFromBasketNonexistentNeed() {
        Need need = new Need("Nonexistent Need", "Description", "Category", 100, 0);
        assertFalse(fundingBasket.removeFromBasket(need));
    }

    @Test
    public void testCheckout(){
        Need need = new Need("Test Need", "Description", "Category", 100, 5);
        fundingBasket.addToBasket(need);
        assertTrue(fundingBasket.getBasket().size() > 0);
        assertTrue(fundingBasket.checkout());
        assertTrue(fundingBasket.getBasket().size() == 0);
    }

    @Test
    public void testIncrement(){
        Need need = new Need("Test Need", "Description", "Category", 100, 5);
        Need copy = new Need(need);
        copy.setQuantity(1);
        fundingBasket.addToBasket(copy);
        copy.setQuantity(2);
        fundingBasket.addToBasket(copy);
        List<Need> needs = fundingBasket.getBasket();
        assertEquals(2, needs.get(0).getQuantity());
    }

    @Test
    public void testDecrement(){
        Need need = new Need("Test Need", "Description", "Category", 100, 3);
        assertFalse(fundingBasket.decrementNeedInBasket(need));
        fundingBasket.addToBasket(need);
        assertTrue(fundingBasket.decrementNeedInBasket(need));
    }
}
