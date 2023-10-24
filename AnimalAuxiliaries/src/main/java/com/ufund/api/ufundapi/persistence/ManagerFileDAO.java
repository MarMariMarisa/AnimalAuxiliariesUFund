package com.ufund.api.ufundapi.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.User; 
//Manager File still does not exist
//import com.ufund.api.ufundapi.model.Manager;

import java.util.ArrayList;

public class ManagerFileDAO implements UserDAO {

    //private ArrayList<Manager> managers; 
    private ObjectMapper objectMapper; 
    private String filename; 
    
}
