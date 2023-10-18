package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/*
 * NeedFileDAOTest.java
 * @author Chase Balmer 
 */
@Tag("Persistence-tier")
public class NeedFileDAOTest {
    NeedFileDAO needFileDAO;
    Need[] testNeeds;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setupNeedFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testNeeds = new Need[3];
        testNeeds[0] = new Need("dog leash", "a dog leash", "leashes", 25, 5);
        testNeeds[0].setID("need1");
        testNeeds[1] = new Need("Second Need", "Description 2", "Category 2", 30, 7);
        testNeeds[1].setID("need2");
        testNeeds[2] = new Need("Third Need", "Description 3", "Category 3", 15, 3);
        testNeeds[2].setID("need3");

        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"), Need[].class))
            .thenReturn(testNeeds);
        needFileDAO = new NeedFileDAO("doesnt_matter.txt", mockObjectMapper);
    }

    @Test
    public void testGetNeeds() throws IOException {
        // Needs are added in stack order so checking them is in reverse
        Need[] needs = needFileDAO.getNeeds();

        assertEquals(needs.length, testNeeds.length);
        for (int i = 0; i < testNeeds.length; ++i)
            assertEquals(needs[testNeeds.length-1 - i].getId(), testNeeds[i].getId());
    }

    @Test
    public void testFindNeeds() throws IOException {
        Need[] needs = needFileDAO.findNeeds("dog");

        assertEquals(needs.length, 1);
        assertEquals(needs[0], testNeeds[0]);
    }

    @Test
    public void testGetNeed() throws IOException {
        Need need = needFileDAO.getNeed("need2");
        assertEquals(need.getId(), testNeeds[1].getId());
    }

    @Test
    public void testDeleteNeed() throws IOException {
        boolean result = assertDoesNotThrow(() -> needFileDAO.deleteNeed("need1"), "Unexpected exception thrown");

        assertEquals(result, true);
        assertEquals(needFileDAO.cupboard.getEntireCupboard().size(), testNeeds.length - 1);
    }

    @Test
    public void testCreateNeed() throws IOException {
        Need need = new Need("Fourth Need", "Description 4", "Category 4", 10, 2);
        need.setID("need4");

        Need result = assertDoesNotThrow(() -> needFileDAO.createNeed(need), "Unexpected exception thrown");

        assertNotNull(result);
        Need actual = needFileDAO.getNeed("need4");
        assertEquals(actual.getId(), need.getId());
        assertEquals(actual.getName(), need.getName());
    }

    @Test
    public void testUpdateNeed() throws IOException {
        Need need = new Need("Second Need", "Updated Description", "Updated Category", 20, 4);
        need.setID("need2");
        Need result = assertDoesNotThrow(() -> needFileDAO.updateNeed(need), "Unexpected exception thrown");

        assertNotNull(result);
        Need actual = needFileDAO.getNeed("need2");
        assertEquals(actual, need);
    }

    @Test
    public void testSaveException() throws IOException {
        doThrow(new IOException())
            .when(mockObjectMapper)
            .writeValue(any(File.class), any(Need[].class));

        Need need = new Need("Fifth Need", "Description 5", "Category 5", 5, 1);

        assertThrows(IOException.class, () -> needFileDAO.createNeed(need), "IOException not thrown");
    }

    @Test
    public void testGetNeedNotFound() throws IOException {
        Need need = needFileDAO.getNeed("Non-Existent Need");

        assertNull(need);
    }

    @Test
    public void testDeleteNeedNotFound() throws IOException {
        boolean result = assertDoesNotThrow(() -> needFileDAO.deleteNeed("Non-Existent Need"), "Unexpected exception thrown");

        assertEquals(result, false);
        assertEquals(needFileDAO.cupboard.getEntireCupboard().size(), testNeeds.length);
    }

    @Test
    public void testUpdateNeedNotFound() throws IOException {
        Need need = new Need("Non-Existent Need", "New Description", "New Category", 10, 2);

        Need result = assertDoesNotThrow(() -> needFileDAO.updateNeed(need), "Unexpected exception thrown");

        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        doThrow(new IOException())
            .when(mockObjectMapper)
            .readValue(new File("doesnt_matter.txt"), Need[].class);

        assertThrows(IOException.class, () -> new NeedFileDAO("doesnt_matter.txt", mockObjectMapper), "IOException not thrown");
    }
}
