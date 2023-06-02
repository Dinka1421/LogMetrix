/*
package org.tvz.logmetrix.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tvz.logmetrix.dto.FilterDTO;
import org.tvz.logmetrix.service.FilterService;

import java.util.List;

@RestController
@RequestMapping("filters")
public class FilterController {
	
	private final FilterService filterService;
	
	@Autowired
	public FilterController(FilterService filterService) {
		this.filterService = filterService;
	}


	@GetMapping
	public ResponseEntity<List<FilterDTO>> getFilters() {

		var filters = filterService.getFilters();

		return filters.isEmpty() ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(filters, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteFilter(@PathVariable("id") Long id) {

		boolean isDeleted = filterService.deleteFilter(id);

		return isDeleted ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<FilterDTO> addFilter(@RequestBody FilterDTO filter) {

		var addedFilter = filterService.addFilter(filter);

		return addedFilter == null ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(addedFilter, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<FilterDTO> updateFilter(@RequestBody FilterDTO filter) {

		var updatedFilter = filterService.updateFilter(filter);

		return updatedFilter == null ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(updatedFilter, HttpStatus.OK);
	}
}
*/
