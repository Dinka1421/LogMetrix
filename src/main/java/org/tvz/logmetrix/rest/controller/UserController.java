package org.tvz.logmetrix.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tvz.logmetrix.entity.User;
import org.tvz.logmetrix.service.UserService;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){ this.userService = userService; }

    @GetMapping
    public Set<User> getUsers() {
        return userService.getUsers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        Optional<User> addedUser = userService.addUser(user);

        if (addedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedUser.get());
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        var updatedUser = userService.updateUser(user);
        return ResponseEntity.status(updatedUser == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(updatedUser);
    }
}
