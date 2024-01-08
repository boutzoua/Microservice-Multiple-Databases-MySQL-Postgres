package com.kindsonthegenius.epm2.courses;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "coursesEntityManagerFactory",
        basePackages = {"com.kindsonthegenius.epm2.appointments"},
        transactionManagerRef = "coursesTransactionManager"
)
@Profile("courses")
public class AppointmentsDBConfiguration {

    @Bean(name="coursesDatasource")
    @ConfigurationProperties(prefix = "spring.courses.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="coursesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("coursesDatasource") DataSource dataSource
    )
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialec");
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.kindsonthegenius.emp2.appointments")
                .persistenceUnit("Course")
                .build();
    }

    @Bean(name="coursesTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("coursesEntityManagerFactory") EntityManagerFactory entityManagerFactory
    )
    {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
