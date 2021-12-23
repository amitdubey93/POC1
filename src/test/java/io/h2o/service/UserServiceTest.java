package io.h2o.service;

import io.h2o.domain.User;
import io.h2o.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(1L)
                .firstName("amit1")
                .lastName("dubey")
                .city("gurugram")
                .dob(LocalDate.of(2020,12,20))
                .doj(LocalDate.of(2020,12,20))
                .email("amit@gmail.com")
                .pinCode("100001")
                .enabled(true)
                .build();
        userList = new ArrayList<>();
        userList.add(user);

    }

    @Test
    void saveUser() {
        User user = User.builder()
                .id(1L)
                .firstName("amit1")
                .lastName("dubey")
                .city("gurugram")
                .dob(LocalDate.of(2020,12,20))
                .doj(LocalDate.of(2020,12,20))
                .email("amit@gmail.com")
                .pinCode("100001")
                .enabled(false)
                .build();
        Mockito.when(userService.saveUser(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user.getFirstName(),savedUser.getFirstName());
    }

    @Test
    void getUsers() {
        Mockito.when(userService.getUsers()).thenReturn(userList);
        List<User> usersByName = userService.getUsers();
        assertEquals(1, usersByName.size());
    }

    @Test
    void deleteUserSoft() {
//        Mockito.when(userService.deleteUserSoft(1L)).thenReturn(true);
        boolean b = userService.deleteUserSoft(1L);
        assertTrue(b);
    }

    @Test
    void deleteUserHard() {
//        Mockito.when(userService.deleteUserHard(1L)).thenReturn(true);
        boolean b = userService.deleteUserHard(1L);
        assertTrue(b);
    }

    @Test
    @DisplayName("Get Users based on Valid User FirstName")
    void whenValidUserFirstName_thenUserShouldFound() {
        String firstName = "amit1";
        Mockito.when(userService.getUsersByName(firstName)).thenReturn(userList);
        List<User> usersByName = userService.getUsersByName(firstName);
        assertEquals(firstName, usersByName.get(0).getFirstName());
    }

    @Test
    @DisplayName("Get null when Invalid User FirstName")
    void whenInvalidUserFirstName_thenReturnNull() {
        String firstName = "amit";
        Mockito.when(userService.getUsersByName(firstName)).thenReturn(null);
        List<User> usersByName = userService.getUsersByName(firstName);
        assertNull(usersByName);
    }

    @Test
    @DisplayName("Get Users when Valid User PinCode")
    void whenValidPinCode_thenReturnUserList() {
        String pinCode = "100001";
        Mockito.when(userService.getUsersByPinCode(pinCode)).thenReturn(userList);
        List<User> usersByName = userService.getUsersByPinCode(pinCode);
        assertEquals(pinCode, usersByName.get(0).getPinCode());
    }

    @Test
    @DisplayName("Get null when Invalid User PinCode")
    void whenInvalidPinCode_thenReturnNull() {
        String pinCode = "10";
        Mockito.when(userService.getUsersByPinCode(pinCode)).thenReturn(null);
        List<User> usersByName = userService.getUsersByPinCode(pinCode);
        assertNull(usersByName);
    }

    @Test
    void getUsersOrderByDoj() {
        Mockito.when(userService.getUsersOrderByDoj()).thenReturn(userList);
        List<User> usersByName = userService.getUsersOrderByDoj();
        assertEquals(1, usersByName.size());
    }
}