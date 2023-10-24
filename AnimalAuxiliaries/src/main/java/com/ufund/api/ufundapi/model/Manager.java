package com.ufund.api.ufundapi.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Manager implements User {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("username")
    private final String username;

    private Cupboard cupboard;

    private static final String MANAGER_ID_MODIFIER = "M";

    public Manager(String username, Cupboard cupboard) {
        this.username = username;
        this.cupboard = cupboard;
        this.id = MANAGER_ID_MODIFIER + UUID.randomUUID().toString();
    }

    //search
    public List<Need> searchNeeds(String substring) {
        return cupboard.getNeedsOnName(substring);
    }

    //select
    public Need getNeed(String id){
        return cupboard.getNeedOnID(id);
    }

    //edit -> removes from helper
    public boolean updateNeed(Need need){
        return cupboard.updateNeed(need);
    }

    //retire
    public boolean retireNeed(Need need){
        return cupboard.retireNeed(need.getId());
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}