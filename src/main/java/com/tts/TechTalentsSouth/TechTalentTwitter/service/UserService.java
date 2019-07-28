package com.tts.TechTalentsSouth.TechTalentTwitter.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tts.TechTalentsSouth.TechTalentTwitter.model.Role;
import com.tts.TechTalentsSouth.TechTalentTwitter.model.User;
import com.tts.TechTalentsSouth.TechTalentTwitter.repository.RoleRepository;
import com.tts.TechTalentsSouth.TechTalentTwitter.repository.UserRepository;

@Service
public class UserService {
	private static final String username = null;
	private static UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, 
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

public static User findByUsername(String username) {
    return userRepository.findByUsername(username);
}
    
public List<User> findAll(){
    return (List<User>) userRepository.findAll();
}
    
public void save(User user) {
    userRepository.save(user);
}
public User saveNewUser(User user) {
	   user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	   user.setActive(1);
	   Role userRole = roleRepository.findByRole("USER");
	   user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	   return userRepository.save(user);
	}

	public User getLoggedInUser() {
	   String loggedInUsername = SecurityContextHolder.
	     getContext().getAuthentication().getName();

	   return findByUsername(loggedInUsername);
	}

	}