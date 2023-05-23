package org.tvz.logmetrix.repo;

import org.tvz.logmetrix.entity.User;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMockRepo {

    private final Set<User> mockUsers = Stream.of(
            new User(1L, "Josko", "Jurcevic", "jjurcevic", "jjurcevic@tvz.hr"),
            new User(2L, "Dinka", "Zadro", "dzadro", "dzadro@tvz.hr")
    ).collect(Collectors.toSet());

    public Set<User> getUsers() { return mockUsers; }

    public boolean deleteUser(Long id) { return mockUsers.removeIf(filter -> filter.getId().equals(id)); }

    public Optional<User> addUser(User user){
        if(!mockUsers.contains(user)){
            mockUsers.add(user);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
    public User updateUser(User user) {
        Optional<User> toUpdateOpt = mockUsers.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findAny();

        return toUpdateOpt.map(toUpdate -> {
            toUpdate.setFirstName(user.getFirstName());
            toUpdate.setLastName(user.getLastName());
            toUpdate.setUsername(user.getUsername());
            toUpdate.setEmail(user.getEmail());
            return toUpdate;
        }).orElse(null);
    }
}
