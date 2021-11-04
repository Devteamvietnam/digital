package com.devteam.module.data.batch.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.devteam.module.data.batch.BatchConfiguration;

@Configuration
@ComponentScan(
  basePackages = {"com.devteam.module.data.batch"}
)
@ConfigurationProperties
@EnableConfigurationProperties
@Import(value = {
  BatchConfiguration.class
})
public class MyTaskBatchConfig  {
  @Autowired
  private JobBuilderFactory jobs;

  @Autowired
  private StepBuilderFactory steps;

  @Bean
  public Step stepOne() {
    return steps.get("demo-job-step-one").tasklet(new MyTaskOne()).build();
  }

  @Bean
  public Step stepTwo(){
    return steps.get("demo-job-step-two").tasklet(new MyTaskTwo()).build();
  }

  @Bean("demo-job")
  public Job demoJob(){
    return jobs.get("demo-job").incrementer(new RunIdIncrementer()).start(stepOne()).next(stepTwo()).build();
  }

}
