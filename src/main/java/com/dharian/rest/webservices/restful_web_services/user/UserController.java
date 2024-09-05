package com.dharian.rest.webservices.restful_web_services.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

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
        User user = userDaoService.findById(id);
        if(user == null){
            throw new UserNotFoundException("ID = "+id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
            userDaoService.save(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
         userDaoService.deleteUser(id);
    }
}
