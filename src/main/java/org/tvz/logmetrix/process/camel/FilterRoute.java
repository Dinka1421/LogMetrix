package org.tvz.logmetrix.process.camel;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


@Component
public class FilterRoute extends RouteBuilder {

	private static final String FILTERS_JMS_URI = "jms:queue:tvz.logmetrix.filters";
	private static final Logger classlog = LoggerFactory.getLogger(FilterRoute.class);
	private static final Consumer<String> logIncoming = msg -> classlog.info("Received filter message with len {}.", msg.length());
	
	@Autowired
	private FilterProcessor filterProcessor;

	
	@Override
	public void configure() {
		from(FILTERS_JMS_URI).process()
				.body(String.class, logIncoming)
				.process(filterProcessor);
	}
}
