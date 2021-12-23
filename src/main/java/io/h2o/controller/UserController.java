package io.h2o.controller;


import io.h2o.domain.User;
//import io.h2o.exception.UserNotFoundException;
import io.h2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult result) {
//        System.err.println(result.getModel());
//        System.err.println(result.getAllErrors());
//        System.err.println(result.getErrorCount());
//        System.err.println(result.hasErrors());

        if (result.hasErrors()) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result.getAllErrors());

        User savedUser = userService.saveUser(user);
        System.err.println(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result.getAllErrors());
        User savedUser = userService.saveUser(user);
        System.err.println(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        List<User> userList = userService.getUsers();
        if(userList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found");
        else return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getUsersByName(@PathVariable String name) {
        List<User> userList = userService.getUsersByName(name);
        if(userList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found with the given parameter");
        else return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/pinCode/{pinCode}")
    public ResponseEntity<?> getUsersByPinCode(@PathVariable String pinCode) {
        List<User> userList = userService.getUsersByPinCode(pinCode);
        if(userList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found with the given parameter");
        else return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/orderByDoj/")
    public ResponseEntity<?> getUsersOrderByDoj() {
        List<User> userList = userService.getUsersOrderByDoj();
        if(userList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found");
        else return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<?> deleteUserSoft(@PathVariable Long id){
        boolean deleted = userService.deleteUserSoft(id);
        if(deleted)  return ResponseEntity.ok("User delete status changed");
        else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found");
    }

    @DeleteMapping("/hard/{id}")
    public ResponseEntity<?> deleteUserHard(@PathVariable Long id) {
        boolean deleted = userService.deleteUserHard(id);
        if(deleted)  return ResponseEntity.ok("User Deleted");
        else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found");
    }
}
