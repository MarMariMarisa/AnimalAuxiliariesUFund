


// package com.ufund.api.ufundapi.controller;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertArrayEquals;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import java.io.IOException;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import com.ufund.api.ufundapi.model.Need;
// import com.ufund.api.ufundapi.persistence.NeedFileDAO;

// public class NeedControllerTest {
//         private NeedController needController;
//     private NeedFileDAO mockNeedDAO;

//     @BeforeEach
//     public void setupNeedController() {
//         mockNeedDAO = mock(NeedFileDAO.class);
//         needController = new NeedController(mockNeedDAO);
//     }
//     // @Test
//     // public void testGetNeed() throws IOException {  
//     //     // Setup
//     //     Need need = new Need(1,"Dog Leashes","Help some dogs get leashes","Equipment",20);
//     //     when(mockNeedDAO.getNeed(need.getId())).thenReturn(need);

//     //     // Invoke
//     //     ResponseEntity<Need> response = needController.getNeed(need.getId());

//     //     // Analyze
//     //     assertEquals(HttpStatus.OK,response.getStatusCode());
//     //     assertEquals(need,response.getBody());
//     // }
//     @Test
//     public void testGetNeedNotFound() throws Exception { 
//         // Setup
//         int needID = 99;
//         when(mockNeedDAO.getNeed(needID)).thenReturn(null);

//         // Invoke
//         ResponseEntity<Need> response = needController.getNeed(needID);

//         // Analyze
//         assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
//     }
//     // @Test
//     // public void testGetNeedHandleException() throws IOException { 
//     //     // Setup
//     //     int needID = 99;

//     //     doThrow(new IOException()).when(mockNeedDAO).getNeed(needID);

//     //     // Invoke
//     //     ResponseEntity<Need> response = needController.getNeed(needID);
//     //     // Analyze
//     //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
//     // }
//     // @Test
//     // public void testGetNeeds() throws IOException { 
//     //     // Setup
//     //     Need[] needs = new Need[2];
//     //     needs[0] = new Need();
//     //     needs[1] = new Need();
//     //     when(mockNeedDAO.getNeeds()).thenReturn(needs);

//     //     // Invoke
//     //     ResponseEntity<Need[]> response = needController.getNeeds();

//     //     // Analyze
//     //     assertEquals(HttpStatus.OK,response.getStatusCode());
//     //     assertEquals(needs,response.getBody());
//     // }
    
//     // @Test
//     // public void testGetNeedsHandleException() throws Exception { 
//     //     // Setup
//     //     doThrow(new IOException()).when(mockNeedDAO).getNeeds();

//     //     // Invoke
//     //     ResponseEntity<Need[]> response = needController.getNeeds();

//     //     // Analyze
//     //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
//     // }
//     // @Test
//     // public void testSearchNeeds() throws IOException { 
//     //             // Setup
//     //             String searchString = "Dog";
//     //             Need[] needs = new Need[2];
//     //             needs[0] = new Need(1,"Dog Leashes","Help some dogs get leashes","Equipment",20);
//     //             needs[1] = new Need(2,"Dog Food","Help some dogs get food","Food",25);
//     //             when(mockNeedDAO.getNeeds()).thenReturn(needs);
        
//     //             // Invoke
//     //             ResponseEntity<Need[]> response = needController.searchNeeds(searchString);
        
//     //             // Assert
//     //             assertEquals(HttpStatus.OK, response.getStatusCode());
//     //             assertArrayEquals(needs, response.getBody());
//     // }

//     // @Test
//     // public void testSearchNeedesHandleException() throws IOException { 
//     //     // Setup
//     //     String searchString = "dawg";
//     //     doThrow(new IOException()).when(mockNeedDAO).findNeeds(searchString);

//     //     // Invoke
//     //     ResponseEntity<Need[]> response = needController.searchNeeds(searchString);

//     //     // Analyze
//     //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
//     // }

// }
