package com.ufund.api.ufundapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommunityBoardTest {

    CommunityBoard communityBoard; 

    private final int EXPECTED_INITIAL_SIZE = 20;

    @BeforeEach
    void setUp() {
        CommunityBoard communityBoard = new CommunityBoard(); 
    }

    @Test 
    void testConstructor() {
        assertNotNull(communityBoard);
        assertEquals(EXPECTED_INITIAL_SIZE, communityBoard.getSize()); 
    }
    
    @Test
    void testAddPost() {
        Post post = new Post("Test title", "Test test test");
        communityBoard.addPost(post); 
        assertEquals(EXPECTED_INITIAL_SIZE + 1, communityBoard.getSize());
    }

    @Test
    void testRemovePost() {
        Post post = new Post("Test title", "Test test test"); 
        communityBoard.addPost(post);
        communityBoard.deletePost(post.getId()); 
        assertEquals(EXPECTED_INITIAL_SIZE, communityBoard.getSize());
    }


}
