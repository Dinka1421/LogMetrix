package org.tvz.logmetrix.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tvz.logmetrix.entity.Filter;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
