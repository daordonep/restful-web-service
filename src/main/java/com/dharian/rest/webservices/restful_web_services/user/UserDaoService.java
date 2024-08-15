package com.dharian.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Paco", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Maria", LocalDate.now().minusYears(30)));
        users.add(new User(3, "Jose", LocalDate.now().minusYears(30)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {

        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElse(null);
    }


    public User save(User user) {
        users.add(user);
        return user;
    }
}
