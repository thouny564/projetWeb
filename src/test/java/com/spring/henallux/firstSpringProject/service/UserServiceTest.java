package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDataAccess;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.model.UserUpdateForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserDataAccess userDataAccess;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(UserDataAccess.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userDataAccess, passwordEncoder);
    }

    @Test
    void registerWithValidUserShouldAddUser() {
        User userForm = new User();
        userForm.setUsername("testuser");
        userForm.setPassword("password");
        userForm.setFirstName("John");
        userForm.setFamilyName("Doe");
        userForm.setStreet("Street");
        userForm.setStreetNumber(1);
        userForm.setPostalCode(1000);
        userForm.setCity("City");
        userForm.setPhoneNumber("0123456789");
        userForm.setMailAddress("test5@mail.com");
        userForm.setBirthdate(LocalDate.now().minusYears(20));

        BindingResult errors = new BeanPropertyBindingResult(userForm, "userForm");

        when(userDataAccess.getByUsername("testuser")).thenReturn(null);
        when(userDataAccess.getByMailAddress("test5@mail.com")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");

        userService.register(userForm, errors);

        assertFalse(errors.hasErrors());
        verify(userDataAccess).add(any(User.class));
    }

    @Test
    void registerWithExistingUsernameShouldReject() {
        User existingUser = new User();
        existingUser.setUsername("user");

        when(userDataAccess.getByUsername("user")).thenReturn(existingUser);

        User userForm = new User();
        userForm.setUsername("user");
        userForm.setPassword("password");
        userForm.setFirstName("John");
        userForm.setFamilyName("Doe");
        userForm.setStreet("Street");
        userForm.setStreetNumber(1);
        userForm.setPostalCode(1000);
        userForm.setCity("City");
        userForm.setPhoneNumber("0123456789");
        userForm.setMailAddress("test3@mail.com");
        userForm.setBirthdate(LocalDate.now().minusYears(20));

        BindingResult errors = new BeanPropertyBindingResult(userForm, "userForm");

        userService.register(userForm, errors);

        assertTrue(errors.hasFieldErrors("username"));
        verify(userDataAccess, never()).add(any());
    }

    @Test
    void updateWithValidDataShouldUpdateUser() {
        User currentUser = new User();
        currentUser.setId(1);
        currentUser.setUsername("user2");
        currentUser.setPassword("password");
        currentUser.setFirstName("John");
        currentUser.setFamilyName("Doe");
        currentUser.setStreet("Street");
        currentUser.setStreetNumber(1);
        currentUser.setPostalCode(1000);
        currentUser.setCity("City");
        currentUser.setPhoneNumber("0123456789");
        currentUser.setMailAddress("test2@mail.com");
        currentUser.setBirthdate(LocalDate.now().minusYears(20));

        when(userDataAccess.getByMailAddress("test2@mail.com")).thenReturn(null);
        when(userDataAccess.getByUsername("user2")).thenReturn(currentUser);

        UserUpdateForm userUpdateForm = new UserUpdateForm();
        userUpdateForm.setFamilyName("newUser2");
        userUpdateForm.setFirstName("John");
        userUpdateForm.setStreet("Street");
        userUpdateForm.setCity("City");
        userUpdateForm.setPhoneNumber("0123456789");
        userUpdateForm.setMailAddress("test2@mail.com");
        userUpdateForm.setBirthdate(LocalDate.now().minusYears(20));

        BindingResult errors = new BeanPropertyBindingResult(userUpdateForm, "form");

        userService.update(currentUser, userUpdateForm, errors);

        assertFalse(errors.hasErrors());
        verify(userDataAccess).update(currentUser);
        assertEquals("newUser2", currentUser.getFamilyName());
    }

    @Test
    void updateWithExistingEmailShouldReject() {
        User currentUser = new User();
        currentUser.setId(1);
        currentUser.setUsername("user10");
        currentUser.setPassword("password");
        currentUser.setFirstName("John");
        currentUser.setFamilyName("Doe");
        currentUser.setStreet("Street");
        currentUser.setStreetNumber(1);
        currentUser.setPostalCode(1000);
        currentUser.setCity("City");
        currentUser.setPhoneNumber("0123456789");
        currentUser.setMailAddress("user10@mail.com");
        currentUser.setBirthdate(LocalDate.now().minusYears(20));

        User otherUser = new User();
        otherUser.setId(2);
        otherUser.setUsername("otherUser");
        otherUser.setMailAddress("existing2@mail.com");

        when(userDataAccess.getByMailAddress("existing2@mail.com")).thenReturn(otherUser);
        when(userDataAccess.getByUsername("user10")).thenReturn(currentUser);

        UserUpdateForm form = new UserUpdateForm();
        form.setMailAddress("existing2@mail.com");
        form.setFirstName("John");
        form.setFamilyName("Doe");
        form.setStreet("Street");
        form.setCity("City");
        form.setPhoneNumber("0123456789");
        form.setBirthdate(LocalDate.now().minusYears(20));

        BindingResult errors = new BeanPropertyBindingResult(form, "form");

        userService.update(currentUser, form, errors);

        assertTrue(errors.hasFieldErrors("mailAddress"));
        verify(userDataAccess, never()).update(any());
    }
}
