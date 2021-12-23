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
                .enabled(true)
                .build();
        userList = new ArrayList<>();
        userList.add(user);
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
                .enabled(true)
                .build();

        Mockito.when(userService.saveUser(inputUser))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"city\": \"gurugram\",\n" +
                        "  \"enabled\": true,\n" +
                        "  \"dob\": \"2021-12-21\",\n" +
                        "  \"doj\": \"2021-12-21\",\n" +
                        "  \"email\": \"sumit@gmail.com\",\n" +
                        "  \"firstName\": \"sumit\",\n" +
                        "  \"lastName\": \"dubey\",\n" +
                        "  \"pinCode\": \"100001\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(MockMvcResultMatchers.content().json("{\n" +
//                        "  \"id\": 1,\n" +
//                        "  \"firstName\": \"sumit\",\n" +
//                        "  \"lastName\": \"dubey\",\n" +
//                        "  \"email\": \"sumit@gmail.com\",\n" +
//                        "  \"dob\": \"2021-12-21\",\n" +
//                        "  \"doj\": \"2021-12-21\",\n" +
//                        "  \"city\": \"gurugram\",\n" +
//                        "  \"pinCode\": \"100001\",\n" +
//                        "  \"enabled\": true\n" +
//                        "}"));
    }

    @Test
    void updateUser() throws Exception {

        User inputUser = User.builder()
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

        Mockito.when(userService.saveUser(inputUser))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"city\": \"gurugram\",\n" +
                                "  \"enabled\": true,\n" +
                                "  \"dob\": \"2021-12-21\",\n" +
                                "  \"doj\": \"2021-12-21\",\n" +
                                "  \"email\": \"sumit@gmail.com\",\n" +
                                "  \"firstName\": \"sumit\",\n" +
                                "  \"lastName\": \"dubey\",\n" +
                                "  \"pinCode\": \"100001\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getUsers() throws Exception {
        Mockito.when(userService.getUsers())
                .thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUsersByName() throws Exception {
        Mockito.when(userService.getUsersByName("amit1"))
                .thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/name/amit1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUsersByPinCode() throws Exception {
        Mockito.when(userService.getUsersByPinCode("100001"))
                .thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/pinCode/100001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUsersOrderByDoj() throws Exception {
        Mockito.when(userService.getUsersOrderByDoj())
                .thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/orderByDoj/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenValidUser_thenDeleteUserSoftOk() throws Exception {
        Mockito.when(userService.deleteUserSoft(1L))
                .thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/user/soft/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenInvalidUser_thenDeleteUserSoftNotFound() throws Exception {
        Mockito.when(userService.deleteUserSoft(10L))
                .thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/user/soft/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void whenValidUser_thenDeleteUserHardOk() throws Exception {
        Mockito.when(userService.deleteUserHard(1L))
                .thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/user/hard/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void whenInvalidUser_thenDeleteUserHardNotFound() throws Exception {
        Mockito.when(userService.deleteUserHard(10L))
                .thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/user/hard/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}