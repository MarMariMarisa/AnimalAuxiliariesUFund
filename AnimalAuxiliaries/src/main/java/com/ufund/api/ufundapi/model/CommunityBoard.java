package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList; 

import org.springframework.stereotype.Component;


@Component
public class CommunityBoard {

    @JsonProperty("posts")
    private ArrayList<Post> communityBoard; 

    private static final int INITIAL_BOARD_SIZE = 20; 

    // Constructor
    public CommunityBoard() {
        communityBoard = new ArrayList<>(INITIAL_BOARD_SIZE);
    }

    /**
     * Posts a new posts to the board
     * @param post
     * @return boolean
     */
    public boolean addPost(Post post) {
        return this.communityBoard.add(post); 
    }
    
    /**
     * Deleteds a post from the community board
     * @param post
     * @return boolean
     */
    public boolean removePost(Post post) {
        return this.communityBoard.remove(post); 
    }

    /**
     * Retrieves the entirety of the community board
     * @return ArrayList<Post>
     */
    public ArrayList<Post> getCommunityBoard() {
        return this.communityBoard; 
    }

    /**
     * Retrieves the current size of the community board
     * @return int
     */
    public int getSize() {
        return this.communityBoard.size(); 
    }
}
