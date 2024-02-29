package com.acar.project.services;


import com.acar.project.entities.User;
import com.acar.project.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long userId) {
       return userRepository.findById(userId).orElse(null);
    }

    public  User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User updateUser(Long userId, @RequestBody User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setUserName(newUser.getUserName());
            user.get().setPassword(newUser.getPassword());
            return userRepository.save(user.get());
        }
        return null;
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
