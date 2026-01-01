package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDAO;
import com.spring.henallux.firstSpringProject.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void loadUserByUsername() {
        // Valeur fictive pour le test
        String username = "john";
        User user = new User();
        user.setUsername(username);

        // On définit ce que doit retourner le mock
        when(userDAO.getByUsername(username)).thenReturn(user);

        // Appel de la méthode à tester
        User result = (User) customUserDetailsService.loadUserByUsername(username);

        // Vérifications
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(username);
    }

    @Test
    void loadUserByUsername_shouldThrowException() {
        String username = "unknown";

        when(userDAO.getByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername(username));
    }
}
