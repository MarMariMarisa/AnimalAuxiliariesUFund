package com.ufund.api.ufundapi.model;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Need [id=%d, name=%s,description=%s,type=%s,amount=%d,isInBasket=%s,isFunded=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("description") private String description;
    @JsonProperty("type") private String type;
    @JsonProperty("amount") private int amount;
    @JsonProperty("isInBasket") private String isInBasket;
    @JsonProperty("isFunded") private String isFunded;

    /**
     * Create a need with the given id,name,description,type,and amount
     * @param id The id of the need
     * @param name The name of the need
     * @param description
     * @param type
     * @param amount
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Need(@JsonProperty("id") int id, @JsonProperty("name") String name,@JsonProperty("description") String description,@JsonProperty("type") String type,@JsonProperty("amount") int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.isFunded = "False";
        this.isInBasket = "False";
    }

    /**
     * Retrieves the id of the need
     * @return The id of the need
     */
    public int getId(){
        return id;
    }
    /**
     * Retrieves the name of the need
     * @return The name of the need
     */
    public String getName(){
        return name;
    }
    /**
     * Retrieves the description of the need
     * @return The description of the need
     */
    public String getDescription(){
        return description;
    }
    /**
     * Retrieves the type of the need
     * @return The type of the need
     */
    public String getType(){
        return type;
    }
    /**
     * Retrieves the amount of the need
     * @return The amount of the need
     */
    public int getAmount(){
        return amount;
    }
    /**
     * Retrieves the is funded of the need
     * @return The is funded of the need
     */
    public String getIsFunded(){
        return isFunded;
    }
    /**
     * Retrieves the in basket state of the need
     * @return The in basket state of the need
     */
    public String getInBasket(){
        return isInBasket;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public void setIsFunded(String isFunded){
        this.isFunded = isFunded;
    }
    public void setIsInBasket(String isInBasket){
        this.isInBasket = isInBasket;
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,description,type,amount,isFunded,isInBasket);
    }
}