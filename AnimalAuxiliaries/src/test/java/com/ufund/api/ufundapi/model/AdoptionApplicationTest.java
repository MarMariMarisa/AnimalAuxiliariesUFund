package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Tyler Combs (tec8354)
 */
public class AdoptionApplicationTest {
    
    @Test
    public void testConstructor(){
        //Setup
        String expectedStatus = "pending";
        String expectedHelperId = "";
        String expectedAnimalId = "";
        String expectedHelperUsername = "username";
        String expectedContact = "contact";
        String expectedInformation = "information";

        //Invoke
        AdoptionApplication application = new AdoptionApplication();

        //Analyze
        assertEquals(expectedStatus, application.getStatus());
        assertEquals(expectedHelperId, application.getHelperId());
        assertEquals(expectedAnimalId, application.getAnimalId());
        assertEquals(expectedHelperUsername, application.getHelperUsername());
        assertEquals(expectedContact, application.getContact());
        assertEquals(expectedInformation, application.getInformation());

    }

    @Test
    public void testStatus(){
        //Setup
        String expectedStatus = "accepted";
        AdoptionApplication application = new AdoptionApplication();

        //Invoke
        application.setStatus("accepted");

        //Analyze
        assertEquals(expectedStatus, application.getStatus());

    }

    @Test
    public void testHelperUsername(){
        //Setup
        String expectedHelperUsername = "New Username";
        AdoptionApplication application = new AdoptionApplication();

        //Invoke
        application.setHelperUsername("New Username");

        //Analyze
        assertEquals(expectedHelperUsername, application.getHelperUsername());

    }

    @Test
    public void testContact(){
        //Setup
        String expectedContact = "Phone Number: 111-111-1111";
        AdoptionApplication application = new AdoptionApplication();

        //Invoke
        application.setContact("Phone Number: 111-111-1111");

        //Analyze
        assertEquals(expectedContact, application.getContact());

    }

    @Test
    public void testInformation(){
        //Setup
        String expectedInformation = "I have 3 cats already";
        AdoptionApplication application = new AdoptionApplication();

        //Invoke
        application.setInformation("I have 3 cats already");

        //Analyze
        assertEquals(expectedInformation, application.getInformation());

    }

}
