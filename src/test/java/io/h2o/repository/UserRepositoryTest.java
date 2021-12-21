package io.h2o.repository;

import io.h2o.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        User user = User.builder()
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

        testEntityManager.persist(user);
    }

    @Test
    void whenFindAll_thenReturnUserList(){
        List<User> userList = userRepository.findAll();
        assertEquals(1,userList.size());
    }


    @Test
    void whenFindByFirstName_thenReturnUser() {
        List<User> userList = userRepository.findByFirstName("amit1");
        assertEquals(userList.get(0).getFirstName(),"amit1");
    }

    @Test
    void whenFindByPinCode_thenReturnUser() {
        List<User> userList = userRepository.findByPinCode("100001");
        assertEquals(userList.get(0).getPinCode(),"100001");
    }
}