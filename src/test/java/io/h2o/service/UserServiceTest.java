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

    @BeforeEach
    void setUp() {
        User user1 = User.builder()
                .id(1L)
                .firstName("amit1")
                .lastName("dubey")
                .city("gurugram")
                .dob(LocalDate.of(2020,12,20))
                .doj(LocalDate.of(2020,12,20))
                .email("amit@gmail.com")
                .pinCode("100001")
                .deleted(false)
                .build();
//        User user2 = User.builder()
//                .id(2L)
//                .firstName("amit2")
//                .lastName("dubey")
//                .city("gurugram")
//                .dob(LocalDate.of(2020,12,19))
//                .doj(LocalDate.of(2020,12,19))
//                .email("amit1@gmail.com")
//                .pinCode("100001")
//                .deleted(false)
//                .build();

        List<User> userList = new ArrayList<>();
        userList.add(user1);
//        userList.add(user2);
        Mockito.when(userService.getUsersByName("amit1")).thenReturn(userList);
        Mockito.when(userService.getUsersByPinCode("100001")).thenReturn(userList);
        Mockito.when(userService.getUsers()).thenReturn(userList);
        Mockito.when(userService.getUsersOrderByDoj()).thenReturn(userList);
        Mockito.when(userService.saveUser(user1)).thenReturn(user1);
        Mockito.when(userService.deleteUserSoft(1L)).thenReturn(true);
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
                .deleted(false)
                .build();
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user.getFirstName(),savedUser.getFirstName());
    }

    @Test
    void getUsers() {
        List<User> usersByName = userService.getUsers();
        assertEquals(1, usersByName.size());
    }


//    @Test
//    void deleteUserSoft() {
//        boolean b = userService.deleteUserSoft(1L);
//        assertTrue(b);
//    }

//    @Test
//    void deleteUserHard() {
//    }

    @Test
    @DisplayName("Get Users based on Valid User FirstName")
    void whenValidUserFirstName_thenUserShouldFound() {
        String firstName = "amit1";
        List<User> usersByName = userService.getUsersByName(firstName);
        assertEquals(firstName, usersByName.get(0).getFirstName());
    }

    @Test
    @DisplayName("Get Users based on Valid User PinCode")
    void getUsersByPinCode() {
        String pinCode = "100001";
        List<User> usersByName = userService.getUsersByPinCode(pinCode);
        assertEquals(pinCode, usersByName.get(0).getPinCode());
    }

    @Test
    void getUsersOrderByDoj() {
        List<User> usersByName = userService.getUsersOrderByDoj();
        assertEquals(1, usersByName.size());
    }
}