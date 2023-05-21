package org.tvz.logmetrix.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filters")
public class FilterController {
	
	@GetMapping
	public ResponseEntity<String> getFilters() {
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
}