package com.webtech.ordernbilling;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.webtech.ordernbilling.entity.User;
import com.webtech.ordernbilling.repository.UserRepository;
import com.webtech.ordernbilling.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void testCreatingUser() {

        user = new User("Ömer","12345678","test@mail",5);
        user.setId(1);
    }

    @Test
    void user_isValid() {
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user,result);
    }

    @Test
    void user_hasInvalidUsernameNull() {

        User invalidUsername = new User(null, "12345678", "test@gmail", 10);

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(invalidUsername);
        });

        verify(userRepository, never()).save(any());

    }

    @Test
    void user_hasInvalidPassword() {

        User weakPassword = new User("ömer", "1234567", "test@mail", 2);
        assertThrows(RuntimeException.class, () -> {
            userService.createUser(weakPassword);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    void user_hasNegativeBalance() {

        User negativeBalance = new User("ömer", "12345678", "test@mail.com", -3);

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(negativeBalance);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    void user_DeleteUser() {
        when(userRepository.existsById(user.getId())).thenReturn(true);

        userService.deleteUser(user.getId());

        verify(userRepository).deleteById(user.getId());

    }

    @Test
    void user_UpdateUser() {

        String newName = "ahmet";
        String newPass = "abcdefgh";
        String newMail = "test@gmail.com";
        Long newAccountBalance = 5L;

        User newUser = new User(newName, newPass, newMail, newAccountBalance);

        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        userService.updateUser(user.getId(),newUser);

        verify(userRepository).save(user);

    }

    @Test
    void user_GetUserById() {

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
}
