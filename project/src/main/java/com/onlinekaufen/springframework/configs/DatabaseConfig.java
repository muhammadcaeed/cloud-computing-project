
package com.onlinekaufen.springframework.configs;

import com.onlinekaufen.springframework.database.mappers.BeanMapperSnakeCaseFactory;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration("databaseConfig")
@ComponentScan("com.onlinekaufen.springframework")
public class DatabaseConfig {

    @Autowired
    private DataSource dataSource;

    public DatabaseConfig(){}
    public DatabaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * in case of further argument registration
     * dbi.registerArgumentFactor(new DateTimeArgumentFactory());
     *
     * @return DBI
     */
    @Bean
    public DBI dbiBean() {
        DBI jdbi = new DBI(dataSource);
        jdbi.registerMapper(new BeanMapperSnakeCaseFactory());
        return jdbi;

    }
}

