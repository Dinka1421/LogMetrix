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
        parameters.put("email", user.getEmail());

        Number generatedId = inserter.executeAndReturnKey(parameters);
        if (generatedId != null) {
            user.setId(generatedId.longValue());
            return user;
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        String sql = "UPDATE users SET username = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";
        int affectedRows = jdbc.update(
                sql,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getId()
        );

        if (affectedRows > 0) {
            return user;
        }
        return null;
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email")
        );
    }


}
