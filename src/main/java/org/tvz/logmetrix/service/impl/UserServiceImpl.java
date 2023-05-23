package org.tvz.logmetrix.service.impl;

import org.springframework.stereotype.Service;
import org.tvz.logmetrix.entity.User;
import org.tvz.logmetrix.repo.UserMockRepo;
import org.tvz.logmetrix.service.UserService;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserMockRepo userRepo = new UserMockRepo();

    @Override
    public Set<User> getUsers() {
        return userRepo.getUsers();
    }

    @Override
    public boolean deleteUser(Long id) {return userRepo.deleteUser(id);}

    @Override
    public Optional<User> addUser(User user) { return userRepo.addUser(user);}

    @Override
    public User updateUser(User user) {
        return userRepo.updateUser(user);
    }


}
