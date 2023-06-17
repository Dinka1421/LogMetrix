package org.tvz.logmetrix.util;

import org.tvz.logmetrix.entity.Filter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilterFixture {

	private FilterFixture() {}
	
	
	public static List<Filter> createFilters() {
		List<Filter> filters = new ArrayList<>();

		Filter filter1 = new Filter();
		filter1.setLog(Filter.Log.SYSTEM);
		filter1.setEnvironment(Filter.Environment.DEV);
		filter1.setClient(Filter.Client.BWA);
		filter1.setStartDate(LocalDate.of(2023, 1, 1));
		filter1.setEndDate(LocalDate.of(2023, 12, 31));
		filters.add(filter1);

		Filter filter2 = new Filter();
		filter2.setLog(Filter.Log.DEBUG);
		filter2.setEnvironment(Filter.Environment.TEST);
		filter2.setClient(Filter.Client.CTN);
		filter2.setStartDate(LocalDate.of(2023, 2, 1));
		filter2.setEndDate(LocalDate.of(2023, 11, 30));
		filters.add(filter2);

		Filter filter3 = new Filter();
		filter3.setLog(Filter.Log.ERROR);
		filter3.setEnvironment(Filter.Environment.INT);
		filter3.setClient(Filter.Client.LX);
		filter3.setStartDate(LocalDate.of(2023, 3, 1));
		filter3.setEndDate(LocalDate.of(2023, 10, 31));
		filters.add(filter3);

		return filters;
	}
}
