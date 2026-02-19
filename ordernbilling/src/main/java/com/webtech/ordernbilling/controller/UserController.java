package com.webtech.ordernbilling.controller;

import com.webtech.ordernbilling.entity.User;
import com.webtech.ordernbilling.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path="/add")
    public User createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        logger.info("User with id " + user.getId() + " and with name " + user.getUsername() + " had been created.");
        return createdUser;
    }

    @GetMapping(path="/all")
    public List<User> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        logger.info("All the users had been listed.");

        return allUsers;
    }

    @GetMapping(path="/{id}")
    public User getUserById(@PathVariable Integer id) {
        User reqUser = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " had not been found."));
        logger.info("Request to list user by id had been made.");
        return reqUser;
    }

    @PutMapping(path="/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        User reqUser = userService.updateUser(id, user);
        logger.info("The user with id " + id + " had been updated");
        return  reqUser;
    }

    @PutMapping(path="/{id}/addbalance/{amount}")
    public User addBalance(@PathVariable Integer id, @PathVariable Long amount) {
        Optional<User> reqUser = userService.depositBalance(id,amount);
        return reqUser.orElse(null);
    }

    @PutMapping(path="/{id}/deletebalance/{amount}")
    public User deleteBalance(@PathVariable Integer id, @PathVariable Long amount) {
        Optional<User> reqUser = userService.withdrawBalance(id,amount);
        return reqUser.orElse(null);
    }

    @PutMapping(path="/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User has been deleted successfully");
    }
    
    @PutMapping(path="{id}/addfriend/{friendId}")
    public Optional<User> addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.addFriend(id,friendId);
        return userService.getUserById(id);
    }

    @PutMapping(path="/{id}/addfavourite/{productId}")
    public Optional<User> addFavourite(@PathVariable Integer id, @PathVariable Integer productId) {
        userService.addFavourite(id,productId);
        return userService.getUserById(id);
    }


}
