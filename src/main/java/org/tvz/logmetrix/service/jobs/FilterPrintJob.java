package org.tvz.logmetrix.service.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.tvz.logmetrix.entity.Filter;
import org.tvz.logmetrix.repo.FilterRepository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class FilterPrintJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(FilterPrintJob.class);

    private final FilterRepository filterRepository;

    public FilterPrintJob(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
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

        log.info("Log Count: {}", logCountMap);
        log.info("Environment Count: {}", environmentCountMap);
        log.info("Client Count: {}", clientCountMap);
    }
}
