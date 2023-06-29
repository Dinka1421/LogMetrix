package org.tvz.logmetrix.process.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.tvz.logmetrix.entity.Filter;
import org.tvz.logmetrix.repo.FilterRepository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class FilterStatusJob extends QuartzJobBean {

    private static final String FILTERS_JMS_QUEUE = "tvz.logmetrix.filters";
    
    private final FilterRepository filterRepository;

    private final JmsTemplate jmsTemplate;
    
    public FilterStatusJob(FilterRepository filterRepository, JmsTemplate jmsTemplate) {
        this.filterRepository = filterRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Filter> filters = filterRepository.findAll();

        Map<Filter.Log, Integer> logCountMap = new EnumMap<>(Filter.Log.class);
        Map<Filter.Environment, Integer> environmentCountMap = new EnumMap<>(Filter.Environment.class);
        Map<Filter.Client, Integer> clientCountMap = new EnumMap<>(Filter.Client.class);

        for (Filter filter : filters) {
            Filter.Log logType = filter.getLog();
            logCountMap.put(logType, logCountMap.getOrDefault(logType, 0) + 1);

            Filter.Environment environment = filter.getEnvironment();
            environmentCountMap.put(environment, environmentCountMap.getOrDefault(environment, 0) + 1);

            Filter.Client client = filter.getClient();
            clientCountMap.put(client, clientCountMap.getOrDefault(client, 0) + 1);
        }

        String report = String.format("Log Count: %s%nEnvironment Count: %s%nClient Count: %s",
                logCountMap, environmentCountMap, clientCountMap);
        
        jmsTemplate.convertAndSend(FILTERS_JMS_QUEUE, report);
    }
}
