package org.example.studentmanager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vero-git-hub
 **/
@Document(collection = "students")
public class Student {

    @Id
    @Schema(description = "Unique identifier of the student", example = "6123456789abcdef01234567")
    private String id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Schema(description = "Name of the student", example = "John Doe")
    private String name;

    @NotBlank(message = "Contact details are mandatory")
    @Pattern(regexp = ".+@.+\\..+", message = "Contact details should be a valid email address")
    @Schema(description = "Contact details of the student", example = "john@example.com")
    private String contactDetails;

    @NotBlank(message = "Address is mandatory")
    @Schema(description = "Address of the student", example = "123 Main St")
    private String address;

    @NotBlank(message = "Pincode is mandatory")
    @Pattern(regexp = "\\d{5}", message = "Pincode must be a 5-digit number")
    @Schema(description = "Postal code of the student's address", example = "12345")
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
