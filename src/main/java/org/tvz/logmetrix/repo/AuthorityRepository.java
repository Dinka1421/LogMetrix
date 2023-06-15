package org.tvz.logmetrix.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tvz.logmetrix.entity.Authority;
import org.tvz.logmetrix.entity.User;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {


    Optional<Authority> findByName(String authorityName);
}
