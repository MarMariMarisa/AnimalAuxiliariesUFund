package com.ufund.api.ufundapi.model;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Sarah Payne (sap4735)
 */
@ExtendWith(MockitoExtension.class)
public class CupboardTest {
    Need[] testNeeds;

    Cupboard cupboard; 

    //Needs to test
    //1. Business Logic: tests happy paths and failute paths through each method
    //2. Constructors & Accessors
    //3. Defensive Programming Checks: Validation of method arguments, Illegal Argument Exception, Validation of component State
    //4. Special Methods as Needed
    //5. Exception Handling 

    @BeforeEach
    void setUp() {
        cupboard = new Cupboard();
        testNeeds = new Need[3];
        testNeeds[0] = new Need("dog leash", "a dog leash", "leashes", 25, 5);
        testNeeds[0].setID("need1");
        testNeeds[1] = new Need("Second Need", "Description 2", "Category 2", 30, 7);
        testNeeds[1].setID("need2");
        testNeeds[2] = new Need("Third Need", "Description 3", "Category 3", 15, 3);
        testNeeds[2].setID("need3");
        for(int i = 0; i < testNeeds.length; i++){
            cupboard.addNeed(testNeeds[i]);
        }
    }
    
    @Test
    @DisplayName("Create a default empty cupboard")
    void testCupboardConstructor() {
        assertNotNull(cupboard, "Cupboard is null");
        //these might fail because the get methods return a list created on the values of the maps which might make it null, i'm not sure 
        assertNotNull(cupboard.getEntireCupboard(), "Current Needs is null"); 
        assertNotNull(cupboard.getSurplus()); 
        //assertNotNull(cupboard.getRetiredNeeds(), "Retired needs is null"); 

    }

    @Test
    @DisplayName("Test getNeedOnID")
    void testGetNeedOnId() {
        Need need = cupboard.getNeedOnID("need1");
        assertEquals(testNeeds[0].getId(), need.getId()); 
    }

    @Test 
    @DisplayName("Test isEmpty")
    void testIsEmpty() {
        Cupboard emptyCupboard = new Cupboard(); 
        assertTrue(emptyCupboard.isEmpty(), "isEmpty returns false when the cupboard is empty"); 
        emptyCupboard.addNeed(testNeeds[0]); 
        assertFalse(emptyCupboard.isEmpty(), "isEmpty is returns true when the cupboard is not empty"); 
    }

    @Test 
    @DisplayName("Test getNeedsOnName")
    //relies on cupboard.addNeed
    void testGetNeedsOnName() {
        //setup 
        String prompt1 = "Need"; 
        String prompt2 = "DOG"; 
        String prompt3 = "null"; 
        String prompt4 = "Second Need"; 

        List<Need> expected1 = new ArrayList<Need>(); expected1.add(testNeeds[1]); expected1.add(testNeeds[2]); 
        List<Need> expected2 = new ArrayList<Need>(); expected2.add(testNeeds[0]); 
        List<Need> expected3 = new ArrayList<Need>(); 
        List<Need> expected4 = new ArrayList<Need>(); expected4.add(testNeeds[1]); 

        List<Need> actual1 = cupboard.getNeedsOnName(prompt1); 
        assertEquals(actual1.size(), expected1.size()); 
        for(int i = 0; i < actual1.size(); i++) {
            assertEquals(actual1.get(i).getId(), expected1.get(actual1.size()-1 - i).getId()); 
        }

        List<Need> actual2 = cupboard.getNeedsOnName(prompt2); 
        assertEquals(actual2.size(), expected2.size()); 
        for(int i = 0; i < actual2.size(); i++) {
            assertEquals(actual2.get(i).getId(), expected2.get(i).getId()); 
        }

        List<Need> actual3 = cupboard.getNeedsOnName(prompt3); 
        assertEquals(actual3.size(), expected3.size()); 
        for(int i = 0; i < actual3.size(); i++) {
            assertEquals(actual3.get(i).getId(), expected3.get(i).getId()); 
        }

        List<Need> actual4 = cupboard.getNeedsOnName(prompt4); 
        assertEquals(actual4.size(), expected4.size()); 
        for(int i = 0; i < actual4.size(); i++) {
            assertEquals(actual4.get(i).getId(), expected4.get(i).getId()); 
        }

    }

    
    @Test 
    @DisplayName("Test addNeed, relies on getEntireCupbaord") 
    void testAddNeed() {
        //setup 
        int expectedSize = 4; 
        Need addNeed = new Need();
        assertTrue(cupboard.addNeed(addNeed)); 
        assertEquals(cupboard.getEntireCupboard().size(), expectedSize); 
        assertTrue(cupboard.getEntireCupboard().contains(addNeed)); 

        assertFalse(cupboard.addNeed(null));
    }

    @Test
    @DisplayName("Test updateNeed")
    void testUpdateNeed() {
         Need newNeed = new Need(); 

        assertTrue(cupboard.updateNeed(testNeeds[0])); 
        assertFalse(cupboard.updateNeed(newNeed)); 

        //gives the same id as testNeeds[0]
        newNeed.setID("need1");
        cupboard.addNeed(newNeed);

        assertTrue(cupboard.updateNeed(newNeed)); 
    }

    @Test 
    @DisplayName("Test addToSurplus") 
    void testAddToSurplus() {
        float add = 1.5f; 
        cupboard.addToSurplus(add);
        assertEquals(add, cupboard.getSurplus());
        cupboard.addToSurplus(add);
        assertEquals(add*2, cupboard.getSurplus());
    }

    @Test 
    @DisplayName("Test resetSurplus")
    void testResetSurplus() {
        float add = 1.5f; 
        cupboard.addToSurplus(add);
        cupboard.resetSurplus();
        assertEquals(0.0f, cupboard.getSurplus());
    }


    /*
    @Test
    @DisplayName("Test retireNeed") 
    void testRetireNeed() {
        assertFalse(cupboard.retireNeed("null")); 

        int oldRetiredSize = cupboard.getRetiredNeeds().size(); 
        assertTrue(cupboard.retireNeed(testNeeds[0].getId())); 
        assertTrue(cupboard.getRetiredNeeds().size() > oldRetiredSize); 
    }
    */
}
