package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.CommunityBoard;
import com.ufund.api.ufundapi.model.Post;

@Component
public class PostFileDAO implements PostDAO {

    CommunityBoard communityBoard; 
    private ObjectMapper objectMapper; 
    private String filename; 

    /**
     * Creates a Post File Data Access Object 
     * 
     * @param filename      Filename to read from and write to 
     * @param objectMapper  Provides JSON Object to/from Java Object serialization 
     *                      and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from 
     */
    public PostFileDAO(@Value("${posts.file}") String filename, ObjectMapper objectMapper) {
        this.filename = filename; 
        this.objectMapper = objectMapper; 
        try {
            load(); 
        } catch (IOException e) {
            return; 
        }
    }

    /**
     * Generates an array of {@linkplain Post posts} from the array list 
     * 
     * @return The array of {@link Post posts}, may be empty 
     * @throws IOException
     */
    Post[] getPostArray() throws IOException {
        List<Post> postsList = communityBoard.getCommunityBoard(); 
        return postsList.toArray(new Post[0]); 
    }

    /**
     * Saves the {@linkplain Post posts} from the array list into the file as an array of JSON objects 
     * 
     * @return true if the {@link Post posts} were written succesfully 
     * 
     * @throws IOException when the file cannot be accessed or written to 
     */
    private boolean save() throws IOException {
        Post[] postArray = getPostArray(); 
        objectMapper.writeValue(new File(filename), postArray); 
        return true; 
    }

    /**
     * Losds {@linkplain Post posts} from the JSON file into the array list 
     * 
     * @return true if the file was read successfully 
     * 
     * @throws IOException when the file cannot be accessed or read from 
     */
    private boolean load() throws IOException {
        communityBoard = new CommunityBoard(); 
        Post[] postArray = objectMapper.readValue(new File(filename), Post[].class); 
        for(Post post : postArray) {
            communityBoard.addPost(post); 
        }

        return true; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post[] getPosts() throws IOException {
        synchronized(communityBoard.getCommunityBoard()) {
            return getPostArray(); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getPost(String id) throws IOException {
        synchronized(communityBoard) {
            return communityBoard.getPostOnId(id); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post createPost(Post post) throws IOException {
        synchronized(communityBoard) {
            if(communityBoard.addPost(post)) {
                save(); 
                return post; 
            }
            return null; 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePost(Post post) throws IOException {
        synchronized(communityBoard) {
            if(communityBoard.deletePost(post)) {
                save(); 
                return true; 
            }
            return false; 
        }
    }
    
}
