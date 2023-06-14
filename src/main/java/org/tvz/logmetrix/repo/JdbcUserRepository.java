package org.tvz.logmetrix.repo;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.tvz.logmetrix.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository

public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }
    @Override
    public List<User> getUsers() {
        return List.copyOf(jdbc.query("SELECT * FROM users", this::mapRowToUser));
    }

    @Override
    public boolean deleteUser(Long id) {
        jdbc.update("DELETE FROM users WHERE id = ?", id);
        return false;
    }

    @Override
    public User addUser(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", user.getUsername());
        parameters.put("first_name", user.getFirstName());
        parameters.put("last_name", user.getLastName());
        parameters.put("password", user.getPassword());

        Number generatedId = inserter.executeAndReturnKey(parameters);
        if (generatedId != null) {
            user.setId(generatedId.longValue());
            return user;
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        String sql = "UPDATE users SET username = ?, first_name = ?, last_name = ?, password = ? WHERE id = ?";
        int affectedRows = jdbc.update(
                sql,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getId()
        );

        if (affectedRows > 0) {
            return user;
        }
        return null;
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbc.query(sql, this::mapRowToUser, username);
        if (!users.isEmpty()) {
            return Optional.of(users.get(0));
        }
        return Optional.empty();
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        return user;
    }


}
