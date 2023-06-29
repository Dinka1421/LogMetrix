package org.tvz.logmetrix.service.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String VACCINE_PRINT_JOB_IDENTITY = "filterPrintJob";
    private static final String VACCINE_PRINT_TRIGGER = "filterPrintTrigger";

    @Bean
    public JobDetail filterPrintJobDetail() {
        return JobBuilder.newJob(FilterPrintJob.class).withIdentity(VACCINE_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger filterPrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(filterPrintJobDetail())
                .withIdentity(VACCINE_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }

}
