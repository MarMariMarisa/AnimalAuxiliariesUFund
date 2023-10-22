package com.ufund.api.ufundapi.model;

import java.util.logging.Logger;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Need Class 
 * Represents something needed by the coorporation. Holds many pieces of data, will be a current until it is fully funded. 
 * 
 */
public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Need [id=%d, name=%s,description=%s,type=%s,price=%d,isInBasket=%s,quantityFunded=%s]";

    // Private State
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private String type;
    @JsonProperty("price")
    private float price;
    // number of this need to be listed as available
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("numInBaskets")
    private int numInBaskets;
    @JsonProperty("quantityFunded")
    private int quantityFunded;

    // Default Values
    private static final String DEFAULT_NAME = "Need";
    private static final String DEFAULT_DESCRIPTION = "";
    private static final String DEFAULT_TYPE = "equipment";
    private static final float DEFAULT_PRICE = 1;
    private static final int DEFAULT_QUANTITY = 1;
    private static final int DEFAULT_NUM_IN_BASKETS = 0;
    private static final int DEFAULT_QUANTITY_FUNDED = 0;

    /**
     * Create a need with all default values
     */
    public Need() {
        this.id = UUID.randomUUID().toString();
        this.name = DEFAULT_NAME;
        this.description = DEFAULT_DESCRIPTION;
        this.type = DEFAULT_TYPE;
        this.price = DEFAULT_PRICE;
        this.quantity = DEFAULT_QUANTITY;
        this.numInBaskets = DEFAULT_NUM_IN_BASKETS;
        this.quantityFunded = DEFAULT_QUANTITY_FUNDED;
    }

    /**
     * Create a need with the given values
     * 
     * @param name
     * @param description
     * @param type
     * @param price
     * @param quantity
     * 
     */
    public Need(@JsonProperty("name") String name, @JsonProperty("description") String description,
            @JsonProperty("type") String type, @JsonProperty("price") float price,
            @JsonProperty("quantity") int quantity) {
        // TODO: Implement checking to prevent needs with faulty values being added
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.numInBaskets = DEFAULT_NUM_IN_BASKETS;
        this.quantityFunded = DEFAULT_QUANTITY_FUNDED;
    }

    // Methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Need other = (Need) obj;
        return id.equals(other.id);
}

    /**
     * Retrieves the decimal ratio of how many of the needs have been funded out of
     * the total quantity needed
     * 
     * @return the percentage of the available quantity of needs that have been
     *         funded
     */
    public float getPercentFunded() {
        return ((float) quantityFunded / (float) quantity) * 100;
    }

    public void setID(String id) {
        this.id = id;
    }

    /**
     * Returns true if the full quantity of needs have been placed in a Helper
     * basket, false if not
     * 
     * @return boolean
     */
    public boolean getAllInBasket() {
        if (this.quantity == this.numInBaskets)
            return true;
        return false;
    }

    /**
     * Returns true if the full quantity of needs has been funded, false otherwise
     * 
     * @return boolean
     */
    public boolean getAllFunded() {
        if (this.quantity == this.quantityFunded)
            return true;
        return false;
    }

    /**
     * Retrieves the id of the need
     * 
     * @return The id of the need
     */
    public String getId() {
        return this.id;
    }

    /**
     * Retrieves the name of the need
     * 
     * @return The name of the need
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the need
     * 
     * @return The description of the need
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the type of the need
     * 
     * @return The type of the need
     */
    public String getType() {
        return this.type;
    }

    /**
     * Retrieves the price of the need
     * 
     * @return The price of the need
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * Retrieves the quantity of the nees
     * 
     * @return The quantity of the need
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Retrieves the number of Helper baskets that the need is placed in
     * 
     * @return the number of Helper baskets that the need is placed in
     */
    public int getNumInBaskets() {
        return this.numInBaskets;
    }

    /**
     * Retreives the number of times that the need has been funded/bought
     * 
     * @return the number of times that the need has been funded/bought
     */
    public int getQuantityFunded() {
        return this.quantityFunded;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, name, description, type, price, quantity, numInBaskets, quantityFunded);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNumInBaskets(int numInBaskets) {
        this.numInBaskets = numInBaskets;
    }

    public void setQuantityFunded(int quantityFunded) {
        this.quantityFunded = quantityFunded;
    }
}