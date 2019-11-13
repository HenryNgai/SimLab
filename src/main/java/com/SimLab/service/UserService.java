package com.SimLab.service;


import com.SimLab.model.dao.Course;
import com.SimLab.model.dao.Repository.CourseRepository;
import com.SimLab.model.dao.Role;
import com.SimLab.model.dao.Repository.RoleRepository;
import com.SimLab.model.dao.User;
import com.SimLab.model.dao.Repository.UserRepository;
//import com.SimLab.model.dao.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
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

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public List<User> findAllInstructors(){ return userRepository.findAllUsingRole("INSTRUCTOR");}
    public List<User> findAllStudents(){ return userRepository.findAllUsingRole("STUDENT");}

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("STUDENT");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public List getAllStudents(){
        return userRepository.getAllStudents();
    }

    public List getAllInstructors(){
        return userRepository.getAllInstructors();
    }


}