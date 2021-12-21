package io.h2o.controller;

import io.h2o.domain.User;
import io.h2o.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;
    private List<User> userList;
    @BeforeEach
    void setUp() {
        user = User.builder()
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
    }

    @Test
    void saveUser() throws Exception {
        User inputUser = User.builder()
//                .id(1L)
                .firstName("amit1")
                .lastName("dubey")
                .city("gurugram")
                .dob(LocalDate.of(2020,12,20))
                .doj(LocalDate.of(2020,12,20))
                .email("amit@gmail.com")
                .pinCode("100001")
                .deleted(false)
                .build();

        userList = new ArrayList<>();
        userList.add(user);
        Mockito.when(userService.saveUser(inputUser))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "                \"firstName\": \"amit1\",\n" +
                        "                \"lastName\": \"dubey\",\n" +
                        "                \"email\": \"amit@gmail.com\",\n" +
                        "                \"dob\": \"2021-12-20\",\n" +
                        "                \"doj\": \"2021-12-20\",\n" +
                        "                \"city\": \"gurugram\",\n" +
                        "                \"pinCode\": \"100001\",\n" +
                        "                \"deleted\": false\n" +
                        "        }"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

//    @Test
//    void updateUser() {
//    }
//
//    @Test
//    void getUsers() {
//    }
//
    @Test
    void getUsersByName() throws Exception {
        Mockito.when(userService.getUsersByName("amit1"))
                .thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/name/amit1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
//
//    @Test
//    void getUsersByPinCode() {
//    }
//
//    @Test
//    void getUsersOrderByDoj() {
//    }
//
//    @Test
//    void deleteUserSoft() {
//    }
//
//    @Test
//    void deleteUserHard() {
//    }
}