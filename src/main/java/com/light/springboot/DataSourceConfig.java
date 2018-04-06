package com.light.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	@Bean(name = "globalDataSource")
	@Qualifier("globalDataSource")
	@Primary	//指定主连接
	@ConfigurationProperties(prefix = "spring.datasource.global")
	public DataSource globalDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "globalJdbcTemplate")
    public JdbcTemplate globalJdbcTemplate(
    		@Qualifier("globalDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}