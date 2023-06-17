package org.tvz.logmetrix.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.tvz.logmetrix.entity.Filter;
import org.tvz.logmetrix.service.FilterService;
import org.tvz.logmetrix.service.mapper.FilterMapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.tvz.logmetrix.util.FilterFixture.createFilters;

@Import(FilterController.class)
class FilterControllerTest extends BaseControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	FilterMapper filterMapper;
	
	@MockBean
	FilterService filterService;
	
	final List<Filter> filtersFixture = createFilters();


	@Test
	void getFilters() throws Exception {
		when(filterService.getFilters()).thenReturn(
				filtersFixture.stream().map(filterMapper::toDTO).collect(Collectors.toList()));
		
		mockMvc.perform(get("/filters"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(filtersFixture.size())));
	}

/*
	@Test
	void deleteFilter() {
	}

	@Test
	void addFilter() {
	}

	@Test
	void updateFilter() {
	}*/
}
