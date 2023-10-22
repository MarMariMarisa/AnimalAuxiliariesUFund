// package com.ufund.api.ufundapi;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// import com.ufund.api.ufundapi.controller.NeedController;
// import com.ufund.api.ufundapi.controller.SearchController;
// import com.ufund.api.ufundapi.model.Cupboard;
// import com.ufund.api.ufundapi.model.Need;

// public class sprint1Demo {
//     public static void main (String [] args){
//         Scanner reader = new Scanner(System.in);
//         // Setup Controllers and such
//         NeedController needController = new NeedController();
//         SearchController searchController = new SearchController();
//         Cupboard cupboard = new Cupboard();

//         reader.nextLine();

//         // Build Needs
//         String nameOne = "Dog Leash";
//         String description = "The dogs we have are going to need leashes";
//         String typeOne = "Equipment";
//         double amount = 15;
//         boolean isInBasket = false;
//         boolean isFunded = false;
//         String idOne = "1";
        
//         String nameTwo = "Dog food";
//         String descriptionTwo = "The dogs are going to need food";
//         String typeTwo = "Food";
//         double amountTwo = 20;
//         String idTwo = "2";

//         String nameThree = "Cat Toys";
//         String descriptionThree = "The cats need toys to play with";
//         double amountThree = 7;
//         String idThree = "3";

//         // Add needs to cupbaord
//         System.out.println("Here is the entire cupboard before need additions:");
//         printCupboard(cupboard, searchController);
//         reader.nextLine();
//         System.out.print("\033[H\033[2J");  
//         System.out.flush();  

        
//         needController.createNeed(idOne, nameOne, description, typeOne, amount, isInBasket, isFunded, cupboard);
//         needController.createNeed(idTwo, nameTwo, descriptionTwo, typeTwo, amountTwo, isInBasket, isFunded, cupboard);   
//         needController.createNeed(idThree, nameThree, descriptionThree, typeOne, amountThree, isInBasket, isFunded, cupboard);
//         System.out.println("\n\nHere is the entire cupboard after need additions:");   
//         printCupboard(cupboard, searchController);
//         reader.nextLine();
//         System.out.print("\033[H\033[2J");  
//         System.out.flush();  
        
//         // Searching for specific need 
//         List<Need> specificNeed = searchController.findNeedName(nameOne, cupboard);
//         System.out.println("\n\nSearch ran for the specific need " + nameOne + " - Results:");
//         System.out.println(specificNeed.get(0).toString());
//         reader.nextLine();
//         System.out.print("\033[H\033[2J");  
//         System.out.flush();  
        

//         // Searching for need by partial name
//         List<Need> potentialNeeds = searchController.findNeedName("Dog", cupboard);
//         System.out.println("\n\nSearch ran for needs containing the partial name - dog - Results:");
//         for(Need need : potentialNeeds){
//             System.out.println(need.toString());
//         }
//         reader.nextLine();
//         System.out.print("\033[H\033[2J");  
//         System.out.flush();  

//         // Remove a need, show changes 
//         needController.removeNeed(nameOne, cupboard);
//         System.out.println("\n\n...We have removed the need for dog leashes, we have enough ->");
//         System.out.println("Here is the entire cupboard after removing dog leashes:");
//         printCupboard(cupboard, searchController);
//         reader.nextLine();
//         System.out.print("\033[H\033[2J");  
//         System.out.flush();  

//         // Update a need 
//         needController.updateNeed(nameThree, nameThree, descriptionThree, typeTwo, 500, isInBasket, isFunded, cupboard);
//         System.out.println("Here is the entire cupboard after updating cat toys:");
//         printCupboard(cupboard, searchController);
//         reader.nextLine();
//         System.out.print("\033[H\033[2J");  
//         System.out.flush();  


//     }

//     static void printCupboard(Cupboard cupboard, SearchController searchController){
//         if(searchController.getEntireCupboard(cupboard).size() == 0){
//             System.out.println("\tIt is empty");
//         }
//         else{
//             for(Need need : searchController.getEntireCupboard(cupboard)){
//                 System.out.println(need.toString());
//             }
//         }
        
//     }
    
// }
