package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Post Class
 * Represents a post made by the manager
 */

 @JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    @JsonProperty("id")
    private int id = -1;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;

    /**
     * Creates an empty post
     */
    public Post() {
        this.id += 1;
        this.title = "";
        this.content = "";
    }

    public Post(@JsonProperty("title") String title, @JsonProperty("content") String content){
        this.id += 1;
        this.title = title;
        this.content = content;
    }

    //Methods

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

}
