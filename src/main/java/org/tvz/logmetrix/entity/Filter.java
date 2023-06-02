/*
package org.tvz.logmetrix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "filters")
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
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Log log;
	
	@Enumerated(EnumType.STRING)
	private Environment environment;
	
	@Enumerated(EnumType.STRING)
	private Client client;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
}
*/
