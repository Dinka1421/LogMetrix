package org.tvz.logmetrix.service;

import org.tvz.logmetrix.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    boolean deleteUser(Long id);
    User addUser(User user);
    User updateUser(User user);
}
