package pl.mrience.jobboard.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "pl.mrience.jobboard.domain")
@ComponentScan(basePackages = "pl.mrience.jobboard")
@EnableJpaRepositories(basePackages = "pl.mrience.jobboard.repository")
public class App {

    public static void main(String [] args) {
        SpringApplication.run(App.class, args);
    }
}
