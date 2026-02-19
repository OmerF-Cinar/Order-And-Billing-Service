package com.webtech.ordernbilling.service;

import com.webtech.ordernbilling.entity.Product;
import com.webtech.ordernbilling.repository.ProductRepository;
import com.webtech.ordernbilling.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtech.ordernbilling.entity.User;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static int MIN_PASSWORD_LENGTH = 8;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    public User createUser(User user) {
        if(user.getUsername() == null || user.getUsername().isBlank()) {
            throw new RuntimeException("The username is not valid!");
        };

        if(user.getPassword() == null || user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new RuntimeException("The password is not strong enough!");
        }

        if(user.getAccountBalance() < 0) {
            throw new RuntimeException("User account balance cannot be negative.");
        }

        User createdUser = userRepository.save(user);
        logger.info("User was created successfully {}", user.getUsername());
        return createdUser;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        logger.info("All users had been listed.");
        return allUsers;
    }

    //Optional to prevent NullPointerException
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User updateUser(Integer id, User userDetails) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userDetails.getUsername());
                    existingUser.setPassword(userDetails.getPassword());
                    existingUser.setEmail(userDetails.getEmail());
                    existingUser.setAccountBalance(userDetails.getAccountBalance());

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User with id " + id + " had not been found."));
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " had not been found.");
        }

        userRepository.deleteById(id);
    }

    public Optional<User> depositBalance(int id, long amount) {
        if (amount < 0) {
            throw new RuntimeException("Deposited balance can't be negative.");
        }

        Optional<User> user = userRepository.findById(id)
                .map(foundUser -> {
                    foundUser.setAccountBalance(foundUser.getAccountBalance() + amount);

                    return userRepository.save(foundUser);
                });
        
        return user;
    }

    public Optional<User> withdrawBalance(int id, long amount) {
        if (amount < 0) {
            throw new RuntimeException("Balance to be withdrawn can't be negative.");
        }

        Optional<User> user = userRepository.findById(id)
                .map(foundUser -> {
                    if (foundUser.getAccountBalance() - amount < 0) {
                        throw new RuntimeException("User balance can't be negative");
                    }
                    foundUser.setAccountBalance(foundUser.getAccountBalance() - amount);

                    return userRepository.save(foundUser);
                });

        return user;
    }

//    public Optional<User> addFriend(int id, int friendId) {
//        Optional<User> userOpt = userRepository.findById(id);
//        Optional<User> friendOpt = userRepository.findById(friendId);
//
//        if (userOpt.isPresent() && friendOpt.isPresent()) {
//            User user = userOpt.get();
//            User friend = friendOpt.get();
//            user.getFriends().add(friend);
//            userRepository.save(user);
//            return Optional.of(user);
//        }
//
//        return Optional.empty();
//    }

    public Optional<User> addFriend(int id, int friendId) {
        Optional<User> userOptional = userRepository.findById(id);
        Optional<User> friendOptional = userRepository.findById(friendId);

        if(userOptional.isPresent() && friendOptional.isPresent()) {
            User user = userOptional.get();
            User friend = friendOptional.get();
            user.getFriends().add(friend);
            userRepository.save(user);
            return Optional.of(user);
        }

        return Optional.empty();
    }

    public Optional<User> addFavourite(int id, int productId) {
        Optional<User> userOptional = userRepository.findById(id);
        Optional<Product> productOptional = productRepository.findById(productId);

        if(userOptional.isPresent() && productOptional.isPresent()) {
            User user = userOptional.get();
            Product product = productOptional.get();
            user.getFavourites().add(product);
            userRepository.save(user);
            return Optional.of(user);
        }

        return Optional.empty();
    }

}
