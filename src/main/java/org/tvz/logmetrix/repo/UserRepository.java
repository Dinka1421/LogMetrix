package org.tvz.logmetrix.repo;

import org.springframework.stereotype.Repository;
import org.tvz.logmetrix.entity.User;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> getUsers();
    boolean deleteUser(Long id);
    User addUser(User user);
    User updateUser(User user);
}
