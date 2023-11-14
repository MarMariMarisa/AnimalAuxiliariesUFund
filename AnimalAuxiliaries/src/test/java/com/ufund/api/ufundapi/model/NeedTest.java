package com.ufund.api.ufundapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class NeedTest {
    @Test
    public void testConstructor(){
 // Setup
        String expectedName = "Need";
        String expectedDescription = "";
        String expectedType = "equipment";
        int expectedQuantity = 1;
        double expectedPrice  = 1;
        //int expectedNumInBaskets = 0;
        int expectedQuantityFunded = 0;

        // Invoke
        Need need = new Need();
        // Analyze
        assertEquals(expectedName,need.getName());
        assertEquals(expectedDescription,need.getDescription());
        assertEquals(expectedType,need.getType());
        assertEquals(expectedQuantity,need.getQuantity());
        assertEquals(expectedPrice,need.getPrice());
        //assertEquals(expectedNumInBaskets,need.getNumInBaskets());
        assertEquals(expectedQuantityFunded,need.getQuantityFunded());
    }
    @Test 
    public void testName(){
        //Setup
        String expectedName = "Not Dog Leashes";

        // Invoke
        Need need = new Need();
        need.setName(expectedName);
        // Analyze
        assertEquals(expectedName,need.getName());
    }
    @Test
    public void testDesc(){
        // Setup
        String expectedDescription = "Help some cats get leashes";

        // Invoke
        Need need = new Need();
        // Analyze
        need.setDescription(expectedDescription);
        assertEquals(expectedDescription,need.getDescription());
    }
    @Test
    public void testType(){
        // Setup
        String expectedType = "Food";

        // Invoke
        Need need = new Need();
        // Analyze
        need.setType(expectedType);
        assertEquals(expectedType,need.getType());
    }
    @Test
    public void testQuantity(){
        // Setup
        int expectedQuantity = 500;
        // Invoke
        Need need = new Need();
        // Analyze
        need.setQuantity(expectedQuantity);
        assertEquals(expectedQuantity,need.getQuantity());
    }
     @Test
    public void testPrice(){
        // Setup
        int expectedPrice = 50;
        // Invoke
        Need need = new Need();
        // Analyze
        need.setPrice(expectedPrice);
        assertEquals(expectedPrice,need.getPrice());
    }
    /*
      @Test
    public void testNumInBaskets(){
        // Setup
        int expectedNumInBaskets = 5;
        // Invoke
        Need need = new Need();
        // Analyze
        need.setNumInBaskets(expectedNumInBaskets);
        assertEquals(expectedNumInBaskets,need.getNumInBaskets());
    }
    */
      @Test
    public void testQuantiyFunded(){
        // Setup
        int expectedQuantityFunded = 3;
        // Invoke
        Need need = new Need();
        // Analyze
        need.setQuantityFunded(expectedQuantityFunded);
        assertEquals(expectedQuantityFunded,need.getQuantityFunded());
    }
      @Test
    public void testGetPercentFunded(){
        // Setup
        float expectedPercentFunded = 20;
        // Invoke
        Need need = new Need();
        // Analyze
        need.setQuantityFunded(1);
        need.setQuantity(5);
        assertEquals(expectedPercentFunded,need.getPercentFunded());
    }
    /*
    @Test
    public void testGetAllInBasket(){
        // Setup
        boolean expectedOne = false;
        boolean expectedTwo = true;
        // Invoke
        Need need = new Need();
        // Analyze
        need.setQuantity(5);
        need.setNumInBaskets(0);
        assertEquals(expectedOne,need.getAllInBasket());
        need.setNumInBaskets(5);
        assertEquals(expectedTwo,need.getAllInBasket());
    }
    */
    @Test
    public void testGetAllFunded(){
        // Setup
        boolean expectedOne = false;
        boolean expectedTwo = true;
        // Invoke
        Need need = new Need();
        // Analyze
        need.setQuantity(5);
        need.setQuantityFunded(0);
        assertEquals(expectedOne,need.getAllFunded());
        need.setQuantityFunded(5);
        assertEquals(expectedTwo,need.getAllFunded());
    }

    @Test
    public void testEquals(){
        Need need = new Need();
        assertEquals(need, need);
    }

    @Test
    public void testOtherConstructor(){
        Need need = new Need();
        Need need2 = new Need(need);
        assertEquals(need, need2);
        Need need3 = new Need();
        assertNotEquals(need, need3);
    }

    @Test
    public void testEqualsWithNullObject() {
        Need need = new Need("Item 1", "Description 1", "Category 1", 20, 5);
        assertFalse(need.equals(null));
    }

    @Test
    public void compare(){
        Need n = new Need();
        Need n2 = new Need();
        assertNotEquals(n, n2);
        n.setID("id");
        n2.setID("id");
        assertEquals(n, n2);
    }

    @Test
    public void testEqualsSameID() {
        Need need1 = new Need("Item1", "Description1", "Category1", 10.0f, 2);
        need1.setID("testID");

        Need need2 = new Need("Item2", "Description2", "Category2", 15.0f, 3);
        need2.setID("testID");

        assertTrue(need1.equals(need2));
        assertTrue(need2.equals(need1));
    }

    @Test
    public void testEqualsDifferentID() {
        Need need1 = new Need("Item1", "Description1", "Category1", 10.0f, 2);
        need1.setID("testID1");

        Need need2 = new Need("Item2", "Description2", "Category2", 15.0f, 3);
        need2.setID("testID2");

        assertFalse(need1.equals(need2));
        assertFalse(need2.equals(need1));
    }

    @Test
    public void testEqualsSameInstance() {
        Need need = new Need("Item", "Description", "Category", 20.0f, 4);
        need.setID("testID");

        assertTrue(need.equals(need));
    }

    @Test
    public void testEqualsDifferentClass() {
        Need need = new Need("Item", "Description", "Category", 20.0f, 4);
        need.setID("testID");

        String differentClassObject = "Not a Need object";

        assertFalse(need.equals(differentClassObject));
    }

    @Test
    public void testEqualsNull() {
        Need need = new Need("Item", "Description", "Category", 20.0f, 4);
        need.setID("testID");

        assertFalse(need.equals(null));
    }

    @Test
    public void testNeedExtraneosValues(){
        Need need = new Need("Item", "Description", "Category", -100.0f, 4);
        Need need2 = new Need("Item", "Description", "Category", 20.0f, -100);
        assertEquals(0, need.getPrice());
        assertEquals(0, need2.getQuantity());

    }
}
