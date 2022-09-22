package org.san.home.persons;

import org.san.home.commons.monitoring.MonitoringConfig;
import org.san.home.persons.config.ApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Import({MonitoringConfig.class, ApiConfig.class})
@EnableJpaRepositories
@SpringBootApplication
public class PersonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonsApplication.class, args);
    }

}