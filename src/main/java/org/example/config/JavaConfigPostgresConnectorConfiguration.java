package org.example.config;

import org.example.connector.JavaConfigPostgresConnector;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class JavaConfigPostgresConnectorConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("db.properties"));
        return configurer;
    }

    @Bean
    public JavaConfigPostgresConnector javaConfigPostgresConnector(@Value("${login}") String login, @Value("${password}") String password) {
        return new JavaConfigPostgresConnector(login, password, dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new PGSimpleDataSource();
    }

}
