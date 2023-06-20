package org.tvz.logmetrix.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.tvz.logmetrix.dto.FilterDTO;
import org.tvz.logmetrix.entity.Filter;
import org.tvz.logmetrix.repo.FilterRepository;
import org.tvz.logmetrix.service.mapper.FilterMapperImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.tvz.logmetrix.util.FilterFixture.createFilters;

class FilterServiceImplTest extends SpringExtendedBaseTest {
	
	@Mock
	private FilterRepository filterRepository;

	@Spy
	private FilterMapperImpl filterMapper;

	@InjectMocks
	private FilterServiceImpl filterService;

	final List<Filter> filtersFixture = createFilters();

	
	@Test
	void getFilters() {
		List<FilterDTO> filterDTOsFixture = filtersFixture.stream()
				.map(filterMapper::toDTO)
				.toList();
		
		when(filterRepository.findAll()).thenReturn(filtersFixture);

		List<FilterDTO> result = filterService.getFilters();

		assertEquals(filterDTOsFixture.size(), result.size());
		verify(filterRepository, times(1)).findAll();
	}

	@Test
	void deleteFilter_Exists() {
		Long id = 1L;

		when(filterRepository.existsById(id)).thenReturn(true);

		boolean result = filterService.deleteFilter(id);

		assertTrue(result);
		verify(filterRepository, times(1)).existsById(id);
		verify(filterRepository, times(1)).deleteById(id);
	}

	@Test
	void deleteFilter_NotExists() {
		Long id = 1L;

		when(filterRepository.existsById(id)).thenReturn(false);

		boolean result = filterService.deleteFilter(id);

		assertFalse(result);
		verify(filterRepository, times(1)).existsById(id);
		verify(filterRepository, never()).deleteById(id);
	}

	@Test
	void addFilter() {
		Filter filter = filtersFixture.get(0);
		FilterDTO filterDTO = filterMapper.toDTO(filter);

		when(filterRepository.save(any(Filter.class))).thenReturn(filter);

		FilterDTO result = filterService.addFilter(filterDTO);

		assertNotNull(result);
		assertEquals(filterDTO, result);
		verify(filterRepository, times(1)).save(filter);
	}

	@Test
	void updateFilter() {
		Filter filter = filtersFixture.get(0);
		FilterDTO filterDTO = filterMapper.toDTO(filter);

		when(filterRepository.save(any(Filter.class))).thenReturn(filter);

		FilterDTO result = filterService.updateFilter(filterDTO);

		assertNotNull(result);
		assertEquals(filterDTO, result);
		verify(filterRepository, times(1)).save(filter);
	}
}
