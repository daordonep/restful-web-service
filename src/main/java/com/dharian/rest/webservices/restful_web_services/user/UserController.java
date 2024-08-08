package com.dharian.rest.webservices.restful_web_services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserDaoService userDaoService;

    @Autowired
    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userDaoService.findById(id);
    }
}
