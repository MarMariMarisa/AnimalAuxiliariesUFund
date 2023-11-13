package com.ufund.api.ufundapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.Post;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;
import com.ufund.api.ufundapi.persistence.PostFileDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("communityboard")
public class CommunityBoardController {
    private static final Logger LOG = Logger.getLogger(CommunityBoardController.class.getName());
    private PostFileDAO postDAO; 

    public CommunityBoardController(PostFileDAO postDAO) throws IOException { 
        this.postDAO = postDAO; 
    }

    @GetMapping("")
    public ResponseEntity<Post[]> getCommunityBoard() {
        LOG.info("GET /communityboard"); 
        try { 
            return new ResponseEntity<Post[]>(postDAO.getPosts(), HttpStatus.OK); 
        } catch(IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) throws IOException {
        LOG.info("GET /communityboard"+id); 
        try {
            Post post = postDAO.getPost(id); 
            if(post != null) {
                return new ResponseEntity<Post>(post, HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
        } catch(IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @PostMapping("")
    public ResponseEntity<Post> createPost(@RequestBody Post post) { 
        LOG.info("POST /communityboard " + post.getId()); 
        if(post.getId().equals("")) {
            post = new Post(post.getTitle(), post.getContent()); 
        }

        try { 
            if(postDAO.getPost(post.getId()) == null) {
                if(postDAO.createPost(post) == null)
                    return new ResponseEntity<>(HttpStatus.CONFLICT); 
                else
                    return new ResponseEntity<Post>(post, HttpStatus.CREATED); 
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT); 
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    // boolean deletePost(Post post)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable String id) {
        LOG.info("DELETE /communityboard/" + id); 
        try {
            if(postDAO.deletePost(id)) {
                return new ResponseEntity<>(HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

}
