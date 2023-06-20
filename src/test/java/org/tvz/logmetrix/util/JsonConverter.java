package org.tvz.logmetrix.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {

	private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	public static String asJsonString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
