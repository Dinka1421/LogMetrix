package org.tvz.logmetrix.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tvz.logmetrix.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
