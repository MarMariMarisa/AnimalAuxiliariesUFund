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
    private String needFilename;
    private String fundedFileName;

    /**
     * Creates a Need File Data Access Object
     * 
     * @param needFileName     Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization
     *                     and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public NeedFileDAO(@Value("${needs.file}") String needFileName, @Value("${funded.file}") String fundedFileName, ObjectMapper objectMapper, @Lazy HelperFileDAO helperFileDAO) throws IOException {
        this.needFilename = needFileName;
        this.fundedFileName = fundedFileName;
        this.objectMapper = objectMapper;
        this.helperFileDAO = helperFileDAO;
        load(); 
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

    private Need[] getFundedNeedsArray() {
        List<Need> fundedList = cupboard.getFundedNeeds();
        return fundedList.toArray(new Need[0]);
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
        Need[] fundedArray = getFundedNeedsArray();
        objectMapper.writeValue(new File(needFilename), needArray);
        objectMapper.writeValue(new File(fundedFileName), fundedArray);
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
        Need[] needArray = objectMapper.readValue(new File(needFilename), Need[].class);
        Need[] fundedArray = objectMapper.readValue(new File(fundedFileName), Need[].class);

        // Add each need to the cupboard
        for (Need need : needArray) {
            cupboard.addNeed(need);
        }
        for(Need need : fundedArray){
            cupboard.addToFunded(need);
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

    public Need[] getFundedNeeds(){
        synchronized(cupboard){
            return getFundedNeedsArray();
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

                // Update need values if it is in a basket 
                for(Helper h : helperFileDAO.getHelpers()){
                    Need[] needs = helperFileDAO.getBasketNeeds(h.getUsername());
                    for(Need n : needs){
                        if(n.equals(need)){
                            int helperQuantity = n.getQuantity();
                            helperFileDAO.removeFromBasket(h.getUsername(), need.getId());

                            if(need.getQuantity() <= 0){
                                save(); // may throw an IOException
                                return need;
                            }

                            Need helperNewNeed = new Need(need);
                            if(helperQuantity >= need.getQuantity()){
                                for(int i = 0; i < need.getQuantity(); i++){
                                    helperFileDAO.addToBasket(h.getUsername(), need);
                                }
                            }
                            else{
                                System.out.println("HELPER QUANT: " + helperQuantity);
                                helperNewNeed.setQuantity(helperQuantity);
                                for(int i = 0; i < helperQuantity; i++){
                                    helperFileDAO.addToBasket(h.getUsername(), helperNewNeed);
                                }                                    
                            }
                        }
                    }
                    
                }

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
                save();
                return true;
            }
            return false;
        }
    }


}