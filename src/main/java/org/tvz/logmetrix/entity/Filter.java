package org.tvz.logmetrix.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

	public enum Log {
		SYSTEM, DEBUG, ERROR, DATA
	}
	
	public enum Environment {
		DEV, TEST, INT, PROD
	}

	public enum Client {
		BWA("Caribbean Airlines"), 
		CTN("Croatian Airlines"), 
		LX("Lufthansa");
		
		private final String name;
		
		Client(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	private Log log;
	private Environment environment;
	private Client client;
	private LocalDate startDate;
	private LocalDate endDate;
}
