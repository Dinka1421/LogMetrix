package org.tvz.logmetrix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvz.logmetrix.dto.UserDTO;
import org.tvz.logmetrix.entity.Authority;
import org.tvz.logmetrix.entity.User;
import org.tvz.logmetrix.repo.CustomUserRepository;
import org.tvz.logmetrix.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final CustomUserRepository userRepo;

    @Autowired
    public UserServiceImpl(CustomUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepo.getUsers().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepo.deleteUser(id);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = mapDTOToUser(userDTO);
        userRepo.addUser(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = mapDTOToUser(userDTO);
        userRepo.updateUser(user);
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return userRepo.findOneByUsername(username).map(this::mapUserToDTO);
    }

    private UserDTO mapUserToDTO(final User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAuthorities(user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
        return userDTO;
    }

    private User mapDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }
}