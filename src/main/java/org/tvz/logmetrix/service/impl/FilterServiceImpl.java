package org.tvz.logmetrix.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvz.logmetrix.entity.Filter;
import org.tvz.logmetrix.repo.FilterRepository;
import org.tvz.logmetrix.service.FilterService;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {
	
	private final FilterRepository filterRepo;

	@Autowired
	public FilterServiceImpl(FilterRepository filterRepo) {
		this.filterRepo = filterRepo;
	}

	@Override
	public List<Filter> getFilters() {
		return filterRepo.findAll();
	}

	@Override
	public boolean deleteFilter(Long id) {
		boolean exists = filterRepo.existsById(id);
		
		filterRepo.deleteById(id);
		
		return exists;
	}

	@Override
	public Filter addFilter(Filter filter) {
		return filterRepo.save(filter);
	}

	@Override
	public Filter updateFilter(Filter filter) {
		return filterRepo.save(filter);
	}
}
