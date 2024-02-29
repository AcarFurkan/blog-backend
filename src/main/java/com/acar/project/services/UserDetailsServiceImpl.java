package com.acar.project.services;

import com.acar.project.entities.User;
import com.acar.project.repos.UserRepository;
import com.acar.project.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public  UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return  user != null ? JwtUserDetails.create(user) : null;
    }

    public UserDetails loadUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        return user != null ? JwtUserDetails.create(user) : null;
    }
}
