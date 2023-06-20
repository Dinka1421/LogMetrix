package org.tvz.logmetrix.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.tvz.logmetrix.dto.FilterDTO;
import org.tvz.logmetrix.entity.Filter;
import org.tvz.logmetrix.service.FilterService;
import org.tvz.logmetrix.service.mapper.FilterMapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.tvz.logmetrix.util.FilterFixture.createFilters;
import static org.tvz.logmetrix.util.JsonConverter.asJsonString;

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

	@Test
	void deleteFilter() throws Exception {
		Long id = 1L;
		when(filterService.deleteFilter(id)).thenReturn(true);

		mockMvc.perform(delete("/filters/{id}", id))
				.andExpect(status().isNoContent());
	}

	@Test
	void deleteFilter_NotFound() throws Exception {
		Long id = 1L;
		when(filterService.deleteFilter(id)).thenReturn(false);

		mockMvc.perform(delete("/filters/{id}", id))
				.andExpect(status().isNotFound());
	}

	@Test
	void addFilter() throws Exception {
		FilterDTO filterDTO = filterMapper.toDTO(filtersFixture.get(0));

		when(filterService.addFilter(filterDTO)).thenReturn(filterDTO);

		mockMvc.perform(post("/filters")
						.contentType("application/json")
						.content(asJsonString(filterDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(filterDTO.getId()))
				.andExpect(jsonPath("$.log").value(filterDTO.getLog().toString()))
				.andExpect(jsonPath("$.environment").value(filterDTO.getEnvironment().toString()))
				.andExpect(jsonPath("$.client").value(filterDTO.getClient().toString()))
				.andExpect(jsonPath("$.startDate").value(filterDTO.getStartDate().toString()))
				.andExpect(jsonPath("$.endDate").value(filterDTO.getEndDate().toString()));
	}

	@Test
	void addFilter_NotFound() throws Exception {
		FilterDTO filterDTO = filterMapper.toDTO(filtersFixture.get(0));

		when(filterService.addFilter(filterDTO)).thenReturn(null);

		mockMvc.perform(post("/filters")
						.contentType("application/json")
						.content(asJsonString(filterDTO)))
				.andExpect(status().isNotFound());
	}

	@Test
	void updateFilter() throws Exception {
		FilterDTO filterDTO = filterMapper.toDTO(filtersFixture.get(0));

		when(filterService.updateFilter(filterDTO)).thenReturn(filterDTO);

		mockMvc.perform(put("/filters")
						.contentType("application/json")
						.content(asJsonString(filterDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(filterDTO.getId()))
				.andExpect(jsonPath("$.log").value(filterDTO.getLog().toString()))
				.andExpect(jsonPath("$.environment").value(filterDTO.getEnvironment().toString()))
				.andExpect(jsonPath("$.client").value(filterDTO.getClient().toString()))
				.andExpect(jsonPath("$.startDate").value(filterDTO.getStartDate().toString()))
				.andExpect(jsonPath("$.endDate").value(filterDTO.getEndDate().toString()));
	}

	@Test
	void updateFilter_NotFound() throws Exception {
		FilterDTO filterDTO = filterMapper.toDTO(filtersFixture.get(0));

		when(filterService.updateFilter(filterDTO)).thenReturn(null);

		mockMvc.perform(put("/filters")
						.contentType("application/json")
						.content(asJsonString(filterDTO)))
				.andExpect(status().isNotFound());
	}
}
