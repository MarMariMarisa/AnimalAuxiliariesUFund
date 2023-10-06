package com.ufund.api.ufundapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.ufund.api.ufundapi.model.Need;
public class NeedTest {
    @Test
    public void testConstructor(){
 // Setup
        int expectedID = 1;
        String expectedName = "Dog Leashes";
        String expectedDescription = "Help some dogs get leashes";
        String expectedType = "Equipment";
        int expectedAmount = 20;
        // Invoke
        Need need = new Need(1,"Dog Leashes","Help some dogs get leashes","Equipment",20);
        // Analyze
        assertEquals(expectedID, need.getId());
        assertEquals(expectedName,need.getName());
        assertEquals(expectedDescription,need.getDescription());
        assertEquals(expectedType,need.getType());
        assertEquals(expectedAmount,need.getAmount());
    }
    @Test 
    public void testName(){
        //Setup
        String expectedName = "Not Dog Leashes";

        // Invoke
        Need need = new Need(1,"Dog Leashes","Help some dogs get leashes","Equipment",20);
        need.setName(expectedName);
        // Analyze
        assertEquals(expectedName,need.getName());
    }
    @Test
    public void testDesc(){
        // Setup
        String expectedDescription = "Help some cats get leashes";

        // Invoke
        Need need = new Need(1,"Dog Leashes","Help some dogs get leashes","Equipment",20);
        // Analyze
        need.setDescription(expectedDescription);
        assertEquals(expectedDescription,need.getDescription());
    }
    @Test
    public void testType(){
        // Setup
        String expectedType = "Food";

        // Invoke
        Need need = new Need(1,"Dog Food","Help some dogs get some food","Equipment",20);
        // Analyze
        need.setType(expectedType);
        assertEquals(expectedType,need.getType());
    }
    @Test
    public void testAmount(){
        // Setup
        int expectedAmount = 500;
        // Invoke
        Need need = new Need(1,"Dog Leashes","Help some dogs get leashes","Equipment",20);
        // Analyze
        need.setAmount(expectedAmount);
        assertEquals(expectedAmount,need.getAmount());
    }
}
