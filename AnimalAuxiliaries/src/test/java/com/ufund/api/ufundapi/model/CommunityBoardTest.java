package com.ufund.api.ufundapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CommunityBoardTest {

    @Test
    public void testAddPost() {
        CommunityBoard communityBoard = new CommunityBoard();
        Post post = new Post("Title", "Content");

        boolean result = communityBoard.addPost(post);

        assertTrue(result);
        assertEquals(1, communityBoard.getSize());
        assertEquals(post, communityBoard.getCommunityBoard().get(0));
    }

    @Test
    public void testDeletePost() {
        CommunityBoard communityBoard = new CommunityBoard();
        Post post = new Post("Title", "Content");
        communityBoard.addPost(post);

        boolean result = communityBoard.deletePost(post.getId());

        assertTrue(result);
        assertEquals(0, communityBoard.getSize());
    }

    @Test
    public void testGetCommunityBoard() {
        CommunityBoard communityBoard = new CommunityBoard();
        Post post1 = new Post("Title1", "Content1");
        Post post2 = new Post("Title2", "Content2");
        communityBoard.addPost(post1);
        communityBoard.addPost(post2);

        ArrayList<Post> result = communityBoard.getCommunityBoard();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(post1));
        assertTrue(result.contains(post2));
    }

    @Test
    public void testGetPostOnId() {
        CommunityBoard communityBoard = new CommunityBoard();
        Post post = new Post("Title", "Content");
        communityBoard.addPost(post);

        Post result = communityBoard.getPostOnId(post.getId());

        assertNotNull(result);
        assertEquals(post, result);
    }

    @Test
    public void testGetSize() {
        CommunityBoard communityBoard = new CommunityBoard();
        Post post1 = new Post("Title1", "Content1");
        Post post2 = new Post("Title2", "Content2");
        communityBoard.addPost(post1);
        communityBoard.addPost(post2);

        int result = communityBoard.getSize();
        
        assertEquals(2, result);
    }
}