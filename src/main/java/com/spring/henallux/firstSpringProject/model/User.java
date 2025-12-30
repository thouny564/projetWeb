package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.*;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private Integer id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 70, message = "Password must be between 8 and 70 characters")
    private String password;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Family name cannot be null")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    private String familyName;

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


    @NotNull
    private boolean enabled;


    @NotNull(message = "Phone number cannot be null")
    @Size(min = 4, max = 20, message = "Phone number must be between 4 and 20 characters")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 254, message = "Email must be at most 254 characters")
    @Email(message="Mail address invalid")
    private String mailAddress;


    private List<Authority> authorities;

    public User() {}

    public User(String username, String password, String firstName, String familyName, String street,
                Integer streetNumber, Integer postalCode, String city, boolean enabled,
                String phoneNumber, String mailAddress) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.familyName = familyName;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.authorities = new ArrayList<>();
    }



    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getMailAddress() { return mailAddress; }
    public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }



    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public Integer getStreetNumber() { return streetNumber; }
    public void setStreetNumber(Integer streetNumber) { this.streetNumber = streetNumber; }

    public Integer getPostalCode() { return postalCode; }
    public void setPostalCode(Integer postalCode) { this.postalCode = postalCode; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }


    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    @Override
    public boolean isEnabled() { return enabled; }




    public void addAuthority(Authority authority){
        authorities.add(authority);
    }


    public void setAuthorities(List<Authority> authorities) { this.authorities = authorities; }


    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", mail address='" + mailAddress + '\'' +
                ", phone number='" + phoneNumber + '\'' +
                '}';
    }
}
