package org.tvz.logmetrix.process.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String VACCINE_PRINT_JOB_IDENTITY = "filterStatusJob";
    private static final String VACCINE_PRINT_TRIGGER = "filterStatusTrigger";

    @Bean
    public JobDetail filterStatusJobDetail() {
        return JobBuilder.newJob(FilterStatusJob.class).withIdentity(VACCINE_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger filterStatusTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(filterStatusJobDetail())
                .withIdentity(VACCINE_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }

}
