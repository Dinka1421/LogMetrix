package org.tvz.logmetrix.service;

import org.tvz.logmetrix.entity.Filter;

import java.util.List;

public interface FilterService {
	
	List<Filter> getFilters();
	boolean deleteFilter(Long id);
	Filter addFilter(Filter filter);
	Filter updateFilter(Filter filter);
}
