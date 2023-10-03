package com.ufund.api.ufundapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ufund.api.ufundapi.controller.NeedController;
import com.ufund.api.ufundapi.controller.SearchController;
import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;

@SpringBootTest
class UfundApiApplicationTests {

	NeedController controller = new NeedController();
    SearchController searchController = new SearchController();
    String name = "Need One";
    String description = "This is needs one description";
    String type = "This is the needs type";
    double amount = 15;
    boolean isInBasket = false;
    boolean isFunded = false;
    String idOne = "1";
    Need needOne = new Need(idOne,name,description,type,amount,isInBasket,isFunded);
    Cupboard cupboard = new Cupboard();

    String nameTwo = "Need Two";
    String descriptionTwo = "This is needs two description";
    String typeTwo = "This is the needs type two";
    double amountTwo = 20;
    String idTwo = "1";
    Need needTwo = new Need(idTwo,nameTwo,descriptionTwo,typeTwo,amountTwo,isInBasket,isFunded);
    int INITIAL_SIZE = 45;
    

    @Test
    public void testCupboard(){
        cupboard.addNeed(needOne);
        cupboard.addNeed(needTwo);
        //assertEquals(cupboard.getNeed(needOne), needOne);
        //assertEquals(cupboard.getNeed(needTwo),needTwo);

        List<Need> actualNeeds = new ArrayList<>();
        actualNeeds.add(needOne);
        actualNeeds.add(needTwo);

        // assertEquals(cupboard.getEntireCupboard(),actualNeeds);
        // controller.updateNeed(needOne,"New Name!!", description, type, amount, isInBasket, isFunded);
        // assertEquals(cupboard.getNeed(needOne).getName(),"New Name!!");

        // cupboard.removeNeed("New Name!!");
        // assertEquals(cupboard.getNeed(needOne),null);


    }

    @Test
    public void testSearchCupboard(){
        Cupboard cupboard = new Cupboard();
        cupboard.addNeed(needOne);
        cupboard.addNeed(needTwo);
        
        List<Need> actual = new ArrayList<>();
        actual.add(needTwo);

        List<Need> needAmount = searchController.findNeedAmount(20, cupboard);
        assertEquals(needAmount.get(0).getAmount(), needTwo.getAmount());

        List<Need> needType = searchController.findNeedType("This is the needs type two", cupboard);
        assertEquals(needType.get(0).getType(), needTwo.getType());

        List<Need> needName = searchController.findNeedName("Need Two", cupboard);
        assertEquals(needName.get(0).getName(), needTwo.getName());
    }

    @Test
    public void testNeedGetters(){
        assertEquals(needOne.getName(),name);
        assertEquals(needOne.getDescription(),description);
        assertEquals(needOne.getType(),type);
        assertEquals(needOne.getAmount(),amount);
        assertEquals(needOne.isInBasket(),isInBasket);
        assertEquals(needOne.isFunded(),isFunded);
    }

    @Test
    public void testNeedSetters(){
        String newName = "New Name";
        needOne.setName(newName);
        assertEquals(needOne.getName(),newName);

        String newDescription = "New Description";
        needOne.setDescription(newDescription);
        assertEquals(needOne.getDescription(),newDescription);

        String newType = "New type";
        needOne.setType(newType);
        assertEquals(needOne.getType(),newType);

        double newAmount = 20;
        needOne.setAmount(newAmount);
        assertEquals(needOne.getAmount(),newAmount);

        boolean newInBasket = true;
        needOne.setInBasket(newInBasket);
        assertEquals(needOne.isInBasket(),newInBasket);

        boolean newIsFunded = true;
        needOne.setFunded(newIsFunded);
        assertEquals(needOne.isFunded(),newIsFunded);
    }

    

}
