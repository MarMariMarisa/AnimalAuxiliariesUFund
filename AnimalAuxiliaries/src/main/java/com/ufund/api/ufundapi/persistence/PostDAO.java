package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Post;

public interface PostDAO {
    /**
     * Retrieves all {@linkplain Post posts} 
     * 
     * @return An array of {@link Post post} objects, may be empty 
     * 
     * @throws IOException if there is an issue with underlying storage 
     */
    Post[] getPosts() throws IOException;

    /**
     * Retrieves a {@linkplain Post post} with the given id
     * 
     * @param id the id of the {@link Post post} to retrieve 
     * 
     * @return a {@link Post post} object with a matching id 
     * <br> 
     * null if no {@link Post post} with matching id is found
     * 
     * @throws IOException if there is an issue with underlying storage 
     */
    Post getPost(String id) throws IOException;

    /**
     * Creates and saves a {@linkplain Post post}
     * 
     * @param post {@linkplain Post post} object to be created and saved 
     * 
     * @return new {@link Post post} if successful, fale otherwise 
     * 
     * @throws IOException if an issue wit the underlyinh storage 
     */
    Post createPost(Post post) throws IOException;

    /**
     * Deletes a {@linkplain Post post} 
     * 
     * @param post the {@link Post post} to be deleted 
     * 
     * @return true if the {@link Post post} was deleted
     * <br>
     * false if the post does not exist 
     * 
     * @throws IOException if underlying storage cannot be accessed 
     */
    boolean deletePost(String id) throws IOException;
}
