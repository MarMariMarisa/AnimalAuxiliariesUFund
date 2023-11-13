package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PostTest {
    @Test
    public void testPost(){
        //Setup
        String expectedTitle = "";
        String expectedContent = "";

        //Invoke
        Post post = new Post();

        //Analyze
        assertEquals(expectedTitle, post.getTitle());
        assertEquals(expectedContent, post.getContent());

    }

    @Test
    public void testTitle(){
        //Setup
        String expectedTitle = "Important Update";

        //Invoke
        Post post = new Post();
        post.setTitle("Important Update");

        //Analyze
        assertEquals(expectedTitle, post.getTitle());
    }

    @Test
    public void testContent(){
        //Setup
        String expectedContent = "We just got a new shipment of dog leashes. Hooray!";

        //Invoke
        Post post = new Post();
        post.setContent("We just got a new shipment of dog leashes. Hooray!");


        //Analyze
        assertEquals(expectedContent, post.getContent());
    }

}
