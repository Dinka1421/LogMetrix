package org.tvz.logmetrix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvz.logmetrix.dto.UserDTO;
import org.tvz.logmetrix.entity.Authority;
import org.tvz.logmetrix.entity.User;
import org.tvz.logmetrix.repo.AuthorityRepository;
import org.tvz.logmetrix.repo.UserRepository;
import org.tvz.logmetrix.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final AuthorityRepository authorityRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, AuthorityRepository authorityRepo) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
    }


    @Override
    public List<UserDTO> getUsers() {
        return userRepo.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepo.deleteById(id);
        return true;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = mapDTOToUser(userDTO);
        userRepo.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = mapDTOToUser(userDTO);
        userRepo.save(user);
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
        userDTO.setLastName(user.getPassword());
        userDTO.setAuthorities(user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
        return userDTO;
    }

    private User mapDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setAuthorities(
                userDTO.getAuthorities().stream()
                        .map(authorityName -> {
                            return authorityRepo.findByName(authorityName).orElseThrow();
                        })
                        .collect(Collectors.toSet()));
        return user;
    }
}