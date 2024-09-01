package org.example.studentmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vero-git-hub
 **/
@Document(collection = "students")
public class Student {

    @Id
    private String id;
    private String name;
    private String contactDetails;
    private String address;
    private String pincode;

    public Student() {}

    public Student(String name, String contactDetails, String address, String pincode) {
        this.name = name;
        this.contactDetails = contactDetails;
        this.address = address;
        this.pincode = pincode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
