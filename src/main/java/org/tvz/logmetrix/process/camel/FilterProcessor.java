package org.tvz.logmetrix.process.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FilterProcessor implements Processor {

	private static final Logger log = LoggerFactory.getLogger(FilterProcessor.class);
	

	@Override
	public void process(Exchange exchange) {
		String filtersMsg = exchange.getIn().getBody(String.class);
		log.info(filtersMsg);
	}
}
