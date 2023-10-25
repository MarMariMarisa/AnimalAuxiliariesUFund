package com.ufund.api.ufundapi.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

@Tag("persistence-tier")
public class HelperFileDAOTest {
    HelperFileDAO helperFileDAO;
    Helper[] testHelpers;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;
    NeedFileDAO mockNeedFileDAO;

    @BeforeEach
    public void setupHelperFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        mockNeedFileDAO = mock(NeedFileDAO.class);
        testNeeds = new Need[3];
        testNeeds[0] = new Need("dog leash", "a dog leash", "leashes", 25, 5);
        testNeeds[0].setID("need1");
        testNeeds[1] = new Need("Second Need", "Description 2", "Category 2", 30, 7);
        testNeeds[1].setID("need2");
        testNeeds[2] = new Need("Third Need", "Description 3", "Category 3", 15, 3);
        testNeeds[2].setID("need3");

        testHelpers = new Helper[3];
        testHelpers[0] = new Helper("user1");
        testHelpers[0].addToFundingBasket(testNeeds[0]);
        
        testHelpers[1] = new Helper("user2");

        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"), Helper[].class))
            .thenReturn(testHelpers);
        helperFileDAO = new HelperFileDAO("doesnt_matter.txt", mockObjectMapper, mockNeedFileDAO);
    }
}
