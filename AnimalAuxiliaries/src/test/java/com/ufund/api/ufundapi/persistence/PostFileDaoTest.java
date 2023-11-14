package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Post;

public class PostFileDaoTest {
    PostFileDAO postFileDAO;
    ObjectMapper mockObjectMapper;
    Post[] testPosts = { };

    @BeforeEach
    public void setupPostFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        
        
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"), Post[].class))
            .thenReturn(testPosts);
        postFileDAO = new PostFileDAO("doesnt_matter.txt", mockObjectMapper);
    }

    @Test
    public void testGetPosts() throws IOException {
        Post[] posts = postFileDAO.getPosts();
        assertEquals(0, posts.length);

        
        posts = postFileDAO.getPosts();

        assertEquals(testPosts.length, posts.length);
        assertTrue(Arrays.deepEquals(testPosts, posts));
    }

    @Test
    public void testGetPost() throws IOException {
        Post testPost = new Post();
        postFileDAO.createPost(testPost);
        Post post = postFileDAO.getPost(testPost.getId());

        assertEquals(testPost, post);
        assertNull(postFileDAO.getPost("nonexistentId"));
    }

    @Test
    public void testCreatePost() throws IOException {
        assertEquals(0, postFileDAO.getPosts().length);

        Post newPost = new Post();
   

        postFileDAO = new PostFileDAO("doesnt_matter.txt", mockObjectMapper);

        Post createdPost = postFileDAO.createPost(newPost);

        assertEquals(newPost, createdPost);
        assertEquals(1, postFileDAO.getPosts().length);
    }

    @Test
    public void testCreatePostFail() throws IOException {
        Post createdPost = postFileDAO.createPost(null);
        assertNull(createdPost);
    }

    @Test
    public void testDeletePost() throws IOException {
        Post newPost = new Post();
        postFileDAO.createPost(newPost);
        assertTrue(postFileDAO.deletePost(newPost.getId()));
        assertEquals(0, postFileDAO.getPosts().length);
    }

    @Test
    public void testDeletePostNotFound() throws IOException {
        assertFalse(postFileDAO.deletePost("nonexistentId"));
    }

    @Test
    public void testSaveException() throws IOException {
        doThrow(new IOException())
                .when(mockObjectMapper)
                .writeValue(any(File.class), any(Post[].class));

        Post newPost = new Post();

        assertThrows(IOException.class, () -> postFileDAO.createPost(newPost), "IOException not thrown");
    }

    
}
