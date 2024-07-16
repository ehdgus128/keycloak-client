package com.ezace.springkeycloak.service;

import com.ezace.springkeycloak.entity.User;
import com.ezace.springkeycloak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getHashPwd());
        user.setHashPwd(encodedPassword);
        return userRepository.save(user);
    }
}
