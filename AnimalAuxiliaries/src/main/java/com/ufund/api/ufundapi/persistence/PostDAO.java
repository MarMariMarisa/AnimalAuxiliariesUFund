package com.ufund.api.ufundapi.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Post;

public interface PostDAO {
    Post[] getPosts() throws IOException;

    Post getPost(int id) throws IOException;

    Post createPost(Post post) throws IOException;

    Post updatePost(Post post) throws IOException;

    boolean deletePost(int id) throws IOException;
}
