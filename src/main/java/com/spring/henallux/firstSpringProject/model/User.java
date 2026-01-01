package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.*;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.time.LocalDate;




public class User implements UserDetails {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @Size(min = 8, max = 70)
    private String password;

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String familyName;

    @NotNull
    @Size(min = 2, max = 100)
    private String street;

    @NotNull
    @Min(value = 1)
    @Max(value = 9999)
    private Integer streetNumber;

    @NotNull
    @Min(value = 1000)
    @Max(value = 9999)
    private Integer postalCode;

    @NotNull
    @Size(min = 2, max = 50)
    private String city;


    private boolean enabled;


    @NotNull
    @Size(min = 4, max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(max = 254)
    @Email
    private String mailAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;


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

    public User(String username, String password, String firstName, String familyName, String street,
                Integer streetNumber, Integer postalCode, String city, boolean enabled,
                String phoneNumber, String mailAddress, LocalDate birthdate) {

        this(username, password, firstName, familyName, street,
                streetNumber, postalCode, city, enabled, phoneNumber, mailAddress);

        this.birthdate = birthdate;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


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
