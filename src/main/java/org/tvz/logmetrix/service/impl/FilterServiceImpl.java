package org.tvz.logmetrix.service.impl;


import org.springframework.stereotype.Service;
import org.tvz.logmetrix.entity.Filter;
import org.tvz.logmetrix.service.FilterService;
import org.tvz.logmetrix.repo.FilterMockRepo;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {
	
	private final FilterMockRepo filterRepo = new FilterMockRepo();
	
	@Override
	public List<Filter> getFilters() {
		return filterRepo.getFilters();
	}

	@Override
	public boolean deleteFilter(Long id) {
		return filterRepo.deleteFilter(id);
	}

	@Override
	public Filter addFilter(Filter filter) {
		return filterRepo.addFilter(filter);
	}

	@Override
	public Filter updateFilter(Filter filter) {
		return filterRepo.updateFilter(filter);
	}
}
