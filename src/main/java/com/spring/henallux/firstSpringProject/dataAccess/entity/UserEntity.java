package com.spring.henallux.firstSpringProject.dataAccess.entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false)
    private Integer streetNumber;

    @Column(name = "postal_code", nullable = false)
    private Integer postalCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AuthorityEntity> authorities = new ArrayList<>();


    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "mail_address", nullable = false, length = 254, unique = true)
    private String mailAddress;

    @Column(name="birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;



    public UserEntity() {}

    public UserEntity(String username, String password, String firstName, String familyName, String street,
                      Integer streetNumber, Integer postalCode, String city, Boolean enabled,
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
    }


    public UserEntity(String username, String password, String firstName, String familyName, String street,
                      Integer streetNumber, Integer postalCode, String city, Boolean enabled,
                      String phoneNumber, String mailAddress, LocalDate birthdate) {

        this(username, password, firstName, familyName, street,
                streetNumber, postalCode, city, enabled, phoneNumber, mailAddress);

        this.birthdate = birthdate;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }





    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                '}';
    }
}
