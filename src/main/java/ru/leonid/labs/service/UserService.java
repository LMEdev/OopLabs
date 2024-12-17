package ru.leonid.labs.service;

import ru.leonid.labs.entity.User;

public interface UserService {
    User saveUser(String username, String password);
    User findByUsername(String username);
}
