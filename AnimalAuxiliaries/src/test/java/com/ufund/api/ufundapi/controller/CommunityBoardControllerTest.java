package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.model.Post;
import com.ufund.api.ufundapi.persistence.PostFileDAO;

public class CommunityBoardControllerTest {
    private PostFileDAO postFileDAO;
    private CommunityBoardController controller;

    @BeforeEach
    public void setupCommunityBoardController() throws IOException{
        postFileDAO = Mockito.mock(PostFileDAO.class);
        controller = new CommunityBoardController(postFileDAO);
    }

    @Test
    public void getCommunityBoardl() throws IOException {
        Post[] expectedPosts = {new Post(), new Post()};
        when(postFileDAO.getPosts()).thenReturn(expectedPosts);

        ResponseEntity<Post[]> response = controller.getCommunityBoard();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPosts, response.getBody());
    }

    @Test
    public void getPost() throws IOException {
        Post expectedPost = new Post();
        when(postFileDAO.getPost("1")).thenReturn(expectedPost);

        ResponseEntity<Post> response = controller.getPost("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPost, response.getBody());
    }

    @Test
    public void createPost() throws IOException {
        Post newPost = new Post("Title", "Content");
        when(postFileDAO.getPost(newPost.getId())).thenReturn(null);
        when(postFileDAO.createPost(newPost)).thenReturn(newPost);

        ResponseEntity<Post> response = controller.createPost(newPost);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newPost, response.getBody());
        
    }

    @Test
    public void createPostWhenPostExists() throws IOException {
        Post existingPost = new Post();
        when(postFileDAO.getPost(existingPost.getId())).thenReturn(existingPost);

        ResponseEntity<Post> response = controller.createPost(existingPost);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void deletePostWhenPostDeleted() throws IOException {
        when(postFileDAO.deletePost("1")).thenReturn(true);

        ResponseEntity<HttpStatus> response = controller.deletePost("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deletePostWhenPostNotDeleted() throws IOException {
        when(postFileDAO.deletePost("1")).thenReturn(false);

        ResponseEntity<HttpStatus> response = controller.deletePost("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void getCommunityBoardIOException() throws IOException {
        when(postFileDAO.getPosts()).thenThrow(new IOException("Test IOException"));

        ResponseEntity<Post[]> response = controller.getCommunityBoard();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getPostIOException() throws IOException {
        when(postFileDAO.getPost(any())).thenThrow(new IOException("Test IOException"));

        ResponseEntity<Post> response = controller.getPost("1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void createPostIOException() throws IOException {
        Post newPost = new Post("Title", "Content");
        when(postFileDAO.getPost(any())).thenReturn(null);
        when(postFileDAO.createPost(newPost)).thenThrow(new IOException("Test IOException"));

        ResponseEntity<Post> response = controller.createPost(newPost);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void deletePostIOException() throws IOException {
        when(postFileDAO.deletePost(any())).thenThrow(new IOException("Test IOException"));

        ResponseEntity<HttpStatus> response = controller.deletePost("1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void getPostWhenPostNotFound() throws IOException {
        when(postFileDAO.getPost(any())).thenReturn(null);

        ResponseEntity<Post> response = controller.getPost("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createPostWhenPostAlreadyExists() throws IOException {
        Post existingPost = new Post("Existing Title", "Existing Content");
        when(postFileDAO.getPost(existingPost.getId())).thenReturn(existingPost);

        ResponseEntity<Post> response = controller.createPost(existingPost);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
    
}
