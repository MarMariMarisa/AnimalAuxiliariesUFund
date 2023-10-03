package com.ufund.api.ufundapi.persistance;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;

public interface NeedDAO {

    /**
     * The function returns a list of all needs
     * 
     * @param cupboard the cupboard to search for needs
     * @return list of all needs
     * @throws IOException
     */
    Need[] getNeeds(Cupboard cupboard) throws IOException;

    /**
     * The function searches for needs by name using the provided text.
     * 
     * @param text used to search for matching needs
     * @return an array of matching needs
     */
    Need[] searchNeedsByName(String text) throws IOException;
    
    /**
     * The function takes a String id as input and returns the need associated
     * throwing an IOException if an error occurs
     * 
     * @param id The id parameter is a string that represents the identifier of the item you want to
     * retrieve.
     * @return The method is returning the need associated with the id.
     */
    Need getNeed(String id) throws IOException;

    /**
     * The function creates a need and throws an IOException.
     * 
     * @return the need created if sucessful in its creation
     */
    Need createNeed() throws IOException;

    /**
     * The function updates a Need and throws an IOException if an error occurs.
     * 
     * @param need Object to be updated and saved
     * @return updated need if sucessful.
     */
    Need updateNeed(Need need) throws IOException;

    /**
     * The function takes an id as a parameter and deletes a need based on that id.
     * 
     * @param id The id parameter is a string that represents the identifier of the need to be deleted.
     * @return
     */
    Need deleteNeed(String id) throws IOException;
}
