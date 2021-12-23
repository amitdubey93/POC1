package io.h2o.service;

import io.h2o.domain.User;
import javax.validation.Valid;
import java.util.List;

public interface UserService {

    User saveUser(@Valid User user);

    List<User> getUsers();

    boolean deleteUserSoft(Long id);

    boolean deleteUserHard(Long id);

    List<User> getUsersByName(String name);
    List<User> getUsersByPinCode(String pinCode);
    List<User> getUsersOrderByDoj();
}
