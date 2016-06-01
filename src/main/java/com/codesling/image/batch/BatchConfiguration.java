package com.codesling.image.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import com.codesling.image.batch.jdbc.beans.CampaignDTO;

@EnableBatchProcessing
@Configuration
@Import(BatchInfrastructureConfiguration.class)
public class BatchConfiguration{  
//	extends DefaultBatchConfigurer {

	@Autowired
	@Qualifier("batchDS") 
	private DataSource datasource;	

	@Autowired
	@Qualifier("DB2ds") 
	private DataSource db2Source;	

	@Autowired
	private Environment environment;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

    
	@Bean
	@Qualifier("DB2ds")
	public ImgScanItemReader readerSQL() throws Exception{
		return new ImgScanItemReader(db2Source, environment);
	}
	@Bean
	public NovaImgItemProcessor processor() {
		return new NovaImgItemProcessor();
	}
	@Bean
	public NovaImgItemWriter writer() {
		return new NovaImgItemWriter(environment);
	}

	
	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory
				.get("step1")
				.<CampaignDTO, CampaignDTO> chunk(1)
				.reader(readerSQL())
				.processor(processor())
				.writer(writer())
//				.exceptionHandler(
//						(context, throwable) -> System.out
//								.println("[BATCH.ERROR] Skipping record on file. cause="
//										+ ((Exception) throwable).getMessage()))
				.build();
	}

	@Bean
	public Job job() throws Exception {

		return jobBuilderFactory.get("job1")
				.incrementer(new RunIdIncrementer()).flow(step1()).end()
				.build();
	}

}
