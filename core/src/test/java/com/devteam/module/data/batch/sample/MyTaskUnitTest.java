package com.devteam.module.data.batch.sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MyTaskUnitTest extends TestConfig {
  @Autowired
  JobLauncher jobLauncher;

  @Qualifier("demo-job")
  @Autowired
  Job job;

  @BeforeEach
  public void setup() throws Exception {
  }

  @Test
  public void testBatch() throws Exception {
    JobParameters params =
        new JobParametersBuilder()
        .addString("JobID", String.valueOf(System.currentTimeMillis()))
        .toJobParameters();
    jobLauncher.run(job, params);
  }
}