package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entityes.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    boolean deleteUser(Long userId);

    User findUserById(long id);
}
