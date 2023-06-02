package org.tvz.logmetrix.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvz.logmetrix.dto.FilterDTO;
import org.tvz.logmetrix.repo.FilterRepository;
import org.tvz.logmetrix.service.FilterService;
import org.tvz.logmetrix.service.mapper.FilterMapper;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {
	
	private final FilterRepository filterRepo;
	private final FilterMapper filterMapper;

	@Autowired
	public FilterServiceImpl(FilterRepository filterRepo, FilterMapper filterMapper) {
		this.filterRepo = filterRepo;
		this.filterMapper = filterMapper;
	}

	@Override
	public List<FilterDTO> getFilters() {
		return filterRepo.findAll().stream().map(filterMapper::toDTO).toList();
	}

	@Override
	public boolean deleteFilter(Long id) {
		boolean exists = filterRepo.existsById(id);
		
		filterRepo.deleteById(id);
		
		return exists;
	}

	@Override
	public FilterDTO addFilter(FilterDTO filter) {
		var newFilter = filterMapper.toEntity(filter);
		return filterMapper.toDTO(filterRepo.save(newFilter));
	}

	@Override
	public FilterDTO updateFilter(FilterDTO filter) {
		var newFilter = filterMapper.toEntity(filter);
		return filterMapper.toDTO(filterRepo.save(newFilter));
	}
}
