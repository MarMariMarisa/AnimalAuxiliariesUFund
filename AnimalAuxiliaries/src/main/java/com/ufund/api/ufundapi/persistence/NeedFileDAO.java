package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.Need;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Implements the functionality for JSON file-based peristance for Needs
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of
 * this class and injects the instance into other classes as needed
 */
@Component
public class NeedFileDAO implements NeedDAO {
    Cupboard cupboard;
    private HelperFileDAO helperFileDAO;
    private ObjectMapper objectMapper;
    private String filename;

    /**
     * Creates a Need File Data Access Object
     * 
     * @param filename     Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization
     *                     and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public NeedFileDAO(@Value("${needs.file}") String filename, ObjectMapper objectMapper, @Lazy HelperFileDAO helperFileDAO) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        this.helperFileDAO = helperFileDAO;
        load(); // load the needs from the file
    }

    /**
     * Generates an array of {@linkplain Need needs} from the tree map
     * 
     * @return The array of {@link Need needs}, may be empty
     */
    private Need[] getNeedsArray() {
        List<Need> needsList = cupboard.getEntireCupboard();
        return needsList.toArray(new Need[0]);
    }

    private Need[] getFundedNeedArray() {
        List<Need> fundedNeedList = cupboard.getFundedNeeds(); 
        return fundedNeedList.toArray(new Need[0]); 
    }

    private float getSurplus() {
        return cupboard.getSurplus(); 
    }

   

    /**
     * Saves the {@linkplain Need needs} from the map into the file as an array of
     * JSON objects
     * 
     * @return true if the {@link Need needs} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Need[] needArray = getNeedsArray();
        Need[] fundedNeedArray = getFundedNeedArray(); 

        File file = new File(filename); 

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(file, needArray);
        objectMapper.writeValue(file, fundedNeedArray); 
        objectMapper.writeValue(file, getSurplus()); 
        return true;
    }

    /**
     * Loads {@linkplain Need needs} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        cupboard = new Cupboard();

        // Deserializes the JSON objects from the file into an array of needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Need[] needArray = objectMapper.readValue(new File(filename), Need[].class);

        // Add each need to the cupboard
        for (Need need : needArray) {
            cupboard.addNeed(need);
        }

        return true;
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public Need[] getNeeds() throws IOException {
        synchronized (cupboard.getEntireCupboard()) {
            return getNeedsArray();
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public Need[] findNeeds(String containsText) throws IOException {
        synchronized(cupboard){
            return cupboard.getNeedsOnName(containsText).toArray(new Need[0]);
        }
        
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public Need getNeed(String id) throws IOException {
        synchronized(cupboard){
            return cupboard.getNeedOnID(id);
        } 
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public Need createNeed(Need need) throws IOException {
        synchronized (cupboard) {
            if(cupboard.addNeed(need)){
                save(); // may throw an IOException
                return need;
            }
            return null;
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public Need updateNeed(Need need) throws IOException {
        synchronized (cupboard) {

            if(cupboard.updateNeed(need)){

                // // Remove from funding baskets if need is updated 
                // for(Helper h : helperFileDAO.getHelpers()){
                //     helperFileDAO.removeFromBasket(h.getUsername(), need.getId());
                // }

                save(); // may throw an IOException
                return need;
            }
            return null;
        }
    }

    /**
     ** {@inheritDoc}
     */
    @Override
    public boolean deleteNeed(String id) throws IOException {
        synchronized (cupboard) {
            if(cupboard.deleteNeed(id)){
                save(); // may throw an IOException

                // Delete this need from every funding basket 
                for(Helper h : helperFileDAO.getHelpers()){
                    helperFileDAO.removeFromBasket(h.getUsername(), id);
                }

                return true;
            }
            return false;
        }
    }

    public boolean fundNeeds(List<Need> toBeFunded) throws IOException {
        synchronized(cupboard){
            if(cupboard.fundNeeds(toBeFunded)){
                // TODO: Add persistence for funded needs
                save();
                return true;
            }
            return false;
        }
    }


}