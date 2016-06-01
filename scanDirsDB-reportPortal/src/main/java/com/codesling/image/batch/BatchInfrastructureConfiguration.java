package com.codesling.image.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableBatchProcessing
public class BatchInfrastructureConfiguration  {


	@Bean(name = "DB2ds")
	@ConfigurationProperties(prefix = "db2.datasource")
    public DataSource createDB2DataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Primary
    @Bean(name = "batchDS")   
    @ConfigurationProperties(prefix="batch.datasource")
    public DataSource createBatchDataSource() {
        return DataSourceBuilder.create().build();
    }


}
