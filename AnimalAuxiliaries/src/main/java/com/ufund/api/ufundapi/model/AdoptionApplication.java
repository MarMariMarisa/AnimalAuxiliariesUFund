// package com.ufund.api.ufundapi.model;

// import java.util.UUID;

// import com.fasterxml.jackson.annotation.JsonProperty;

// public class AdoptionApplication {
    
//     //Private State
//     @JsonProperty("id")
//     private String id;
//     @JsonProperty("status")
//     private String status;
//     @JsonProperty("helper_id")
//     private String helper_id;
//     @JsonProperty("animal_id")
//     private String animal_id;
//     @JsonProperty("helper_username")
//     private String helper_username;
//     @JsonProperty("contact")
//     private String contact;
//     @JsonProperty("information")
//     private String information;

//     //Default Values
//     private static final String DEFAULT_STATUS = "pending";
//     private static final String DEFAULT_HELPER_ID = "";
//     private static final String DEFAULT_ANIMAL_ID = "";
//     private static final String DEFAULT_HELPER_USERNAME = "username";
//     private static final String DEFAULT_CONTACT = "contact";
//     private static final String DEFAULT_INFORMATION = "information";

//     /**
//      * Creates an Adoption Application with default values
//      * 
//      */
//     public AdoptionApplication(){
//         this.id = UUID.randomUUID().toString();
//         this.status = DEFAULT_STATUS;
//         this.helper_id = DEFAULT_HELPER_ID;
//         this.animal_id = DEFAULT_ANIMAL_ID;
//         this.helper_username = DEFAULT_HELPER_USERNAME;
//         this.contact = DEFAULT_CONTACT;
//         this.information = DEFAULT_INFORMATION;

//     }

//     /**
//      * Creates an application with the given values
//      * 
//      * @param status 
//      * @param helper_id
//      * @param animal_id
//      * @param helper_username
//      * @param contact
//      * @param information
//      */
//     public AdoptionApplication(@JsonProperty("status") String status, @JsonProperty("helper_id") String helper_id, 
//             @JsonProperty("animal_id") String animal_id, @JsonProperty("helper_username") String helper_username, 
//             @JsonProperty("contact") String contact, @JsonProperty("information") String information) {
        
//         this.id = UUID.randomUUID().toString();
//         this.status = status;
//         this.helper_id = helper_id;
//         this.animal_id = animal_id;
//         this.helper_username = helper_username;
//         this.contact = contact;
//         this.information = information;
//     }

//     //Methods

//     /**
//      * 
//      * @return application id
//      */
//     public String getId(){
//         return this.id;
//     }

//     /**
//      * 
//      * @return application status
//      */
//     public String getStatus(){
//         return this.status;
//     }

//     /**
//      * 
//      * @return applications helper_id
//      */
//     public String getHelperId(){
//         return this.helper_id;
//     }

//     /**
//      * 
//      * @return applications animal_id
//      */
//     public String getAnimalId(){
//         return this.animal_id;
//     }

//     /**
//      * 
//      * @return applications helper_username
//      */
//     public String getHelperUsername(){
//         return this.helper_username;
//     }

//     /**
//      * 
//      * @return applications contact
//      */
//     public String getContact(){
//         return this.contact;
//     }

//     /**
//      * 
//      * @return applications information
//      */
//     public String getInformation(){
//         return this.information;
//     }

//     //Setters

//     /**
//      * Updates the status of the application.
//      * @param status new status value
//      * @return boolean if status updated
//      */
//     public boolean setStatus(String status){
//         if(status != null){
//             this.status = status;
//             return true;
//         }
//         return false;
//     }

//     /**
//      * Updates the helper_username of the application
//      * @param helper_username new helper_username value
//      */
//     public void setHelperUsername(String helper_username){
//         this.helper_username = helper_username;
//     }

//     /**
//      * Updates the contact contact of the application
//      * @param contact new contact value
//      */
//     public void setContact(String contact){
//         this.contact = contact;
//     }

//     /**
//      * Updates the information of the application
//      * @param information new informaiton value
//      */
//     public void setInformation(String information){
//         this.information = information;
//     }
// }
