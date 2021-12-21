package io.h2o.service.impl;

import io.h2o.domain.User;
import io.h2o.repository.UserRepository;
import io.h2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(@Valid User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAll();
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Override
    public List<User> getUsersByPinCode(String pinCode) {
        return userRepository.findByPinCode(pinCode);
    }

    @Override
    public List<User> getUsersOrderByDoj() {
        return userRepository.getUsersOrderByDoj();
    }

    @Override
    public boolean deleteUserSoft(Long id) {
        int i = userRepository.deleteUserSoft(id);
        if(i==0) return false;
        else return true;
    }

    @Override
    public void deleteUserHard(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }
}
