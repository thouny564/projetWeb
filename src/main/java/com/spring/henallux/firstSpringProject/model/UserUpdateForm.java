package com.spring.henallux.firstSpringProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserUpdateForm {


    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Family name cannot be null")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    private String familyName;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 254, message = "Email must be at most 254 characters")
    @Email(message = "Mail address invalid")
    private String mailAddress;

    @NotNull(message = "Phone number cannot be null")
    @Size(min = 4, max = 20, message = "Phone number must be between 4 and 20 characters")
    private String phoneNumber;

    @NotNull(message = "Street cannot be null")
    @Size(min = 2, max = 100, message = "Street must be between 2 and 100 characters")
    private String street;

    @NotNull(message = "Street number cannot be null")
    @Min(value = 1, message = "Street number must be at least 1")
    @Max(value = 9999, message = "Street number must be at most 9999")
    private Integer streetNumber;

    @NotNull(message = "Postal code cannot be null")
    @Min(value = 1000, message = "Postal code must be at least 1000")
    @Max(value = 9999, message = "Postal code must be at most 9999")
    private Integer postalCode;

    @NotNull(message = "City cannot be null")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    private String city;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
