package com.example.springboot.scheduler;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;

@Configuration
@EnableAutoConfiguration
public class QuartzScheduler {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    //@QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource quartzDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * While the job is the workhorse, Quartz does not store an actual instance of the job class.
     * Instead, we can define an instance of the Job using the JobDetail class.
     * The job's class must be provided to the JobDetail so that it knows the type of the job to be executed.
     **/
    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(SampleJob.class);
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    /**
     * A Trigger is the mechanism to schedule a Job, i.e. a Trigger instance “fires” the execution of a job.
     * There's a clear separation of responsibilities between the Job
     * (notion of task) and Trigger (scheduling mechanism).
     **/
    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        Date mydate = new Date();
        mydate.setTime(mydate.getTime() + 5000);
        trigger.setStartTime(mydate);
        trigger.setRepeatInterval(60000);
        trigger.setRepeatCount(2);
        return trigger;
    }

    /**
     * The Scheduler interface is the main API for interfacing with the job scheduler.
     * A Scheduler can be instantiated with a SchedulerFactory.
     * Once created, Jobs and Triggers can be registered with it.
     * Initially, the Scheduler is in “stand-by” mode, and its start method must be invoked
     * to start the threads that fire the execution of jobs.
     **/
    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job, DataSource quartzDataSource) throws IOException {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        //Non ho bisogno del quartz.properties dal momento che le props di quartz sono settate nell'application.properties
        //schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        schedulerFactory.setDataSource(quartzDataSource);
        return schedulerFactory;
    }


    /**
     * The SpringBeanJobFactory provides support for injecting the scheduler context,
     * job data map, and trigger data entries as properties into the job bean while creating an instance.
     * However, it lacks support for injecting bean references from the application context.
     **/
    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
}
