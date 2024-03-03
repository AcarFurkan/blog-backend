package com.acar.project.services;


import com.acar.project.entities.User;
import com.acar.project.repos.CommentRepository;
import com.acar.project.repos.LikeRepository;
import com.acar.project.repos.PostRepository;
import com.acar.project.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    PostRepository postRepository;
    CommentRepository commentRepository;
    LikeRepository likeRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
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

    public User findByUserName(String userName) {
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


    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty())
            return null;
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
        List<Object> result = new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;
    }
}
