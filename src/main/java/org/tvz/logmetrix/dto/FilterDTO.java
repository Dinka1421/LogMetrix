package org.tvz.logmetrix.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FilterDTO {

	public enum LogDTO {
		SYSTEM, DEBUG, ERROR, DATA
	}

	public enum EnvironmentDTO {
		DEV, TEST, INT, PROD
	}

	public enum ClientDTO {
		BWA("Caribbean Airlines"),
		CTN("Croatian Airlines"),
		LX("Lufthansa");

		private final String name;

		ClientDTO(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private Long id;
	private LogDTO log;
	private EnvironmentDTO environment;
	private ClientDTO client;
	private LocalDate startDate;
	private LocalDate endDate;
}
