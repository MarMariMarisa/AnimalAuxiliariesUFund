package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.persistence.HelperFileDAO; 

/*
 * Cupboard controller is still being used to add/remove stuff from the cupboard 
 * This class is going to be used for adding/removing/viewing from funding baskets 
 * 
 */
@RestController
@RequestMapping("fundingbasket")
public class HelperController {
    private static final Logger LOG = Logger.getLogger(HelperController.class.getName()); 
    private HelperFileDAO helperDAO;

    public HelperController(HelperFileDAO helperDAO) throws IOException {
        this.helperDAO = helperDAO;
    }

    // Mapping can mirror other stuff
}
