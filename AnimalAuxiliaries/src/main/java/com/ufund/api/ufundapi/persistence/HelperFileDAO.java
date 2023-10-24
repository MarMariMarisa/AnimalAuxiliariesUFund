package com.ufund.api.ufundapi.persistence;

import com.ufund.api.ufundapi.model.User; 
import com.ufund.api.ufundapi.model.Helper;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.FundingBasket; 

public class HelperFileDAO implements UserDAO {

    private ArrayList<Helper> helpers; 
    private ObjectMapper objectMapper; 
    private String filename; 

    public HelperFileDAO() {}

    
}
