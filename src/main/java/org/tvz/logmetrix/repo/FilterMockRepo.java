package org.tvz.logmetrix.repo;

import org.tvz.logmetrix.entity.Filter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilterMockRepo {
	
	private final List<Filter> filters;
	
	public FilterMockRepo() {
		filters = new ArrayList<>();
		
		Filter filter1 = new Filter();
		filter1.setId(1L);
		filter1.setLog(Filter.Log.SYSTEM);
		filter1.setEnvironment(Filter.Environment.DEV);
		filter1.setClient(Filter.Client.BWA);
		filter1.setStartDate(LocalDate.of(2020, 1, 1));
		filter1.setEndDate(LocalDate.of(2020, 1, 31));

		Filter filter2 = new Filter();
		filter2.setId(2L);
		filter2.setLog(Filter.Log.DEBUG);
		filter2.setEnvironment(Filter.Environment.PROD);
		filter2.setClient(Filter.Client.BWA);
		filter2.setStartDate(LocalDate.of(2020, 1, 2));
		filter2.setEndDate(LocalDate.of(2020, 1, 31));
		
		filters.add(filter1);
		filters.add(filter2);
	}
	
	
	public List<Filter> getFilters() {
		return filters;
	}
	
	public boolean deleteFilter(Long id) {
		return filters.removeIf(filter -> filter.getId().equals(id));
	}
	
	public Filter addFilter(Filter filter) {
		filters.add(filter);
		return filter;
	}
	
	public Filter updateFilter(Filter filter) {
		Optional<Filter> toUpdateOpt = filters.stream()
				.filter(f -> f.getId().equals(filter.getId()))
				.findAny();
		
		if (toUpdateOpt.isPresent()) {
			Filter toUpdate = toUpdateOpt.get();
			
			toUpdate.setLog(filter.getLog());
			toUpdate.setEnvironment(filter.getEnvironment());
			toUpdate.setClient(filter.getClient());
			toUpdate.setStartDate(filter.getStartDate());
			toUpdate.setEndDate(filter.getEndDate());
			
			return toUpdate;
		} else {
			return null;
		}
	}
	
}
