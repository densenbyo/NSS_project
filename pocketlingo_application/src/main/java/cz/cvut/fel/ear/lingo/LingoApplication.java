package cz.cvut.fel.ear.lingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication
@EnableJpaRepositories({"cz.cvut.fel.ear.lingo"})
@EntityScan({"cz.cvut.fel.ear.lingo"})
@ComponentScans(value = {@ComponentScan("cz.cvut.fel.ear.lingo.dao"),
        @ComponentScan("cz.cvut.fel.ear.lingo.exception"), @ComponentScan("cz.cvut.fel.ear.lingo.model"),
        @ComponentScan("cz.cvut.fel.ear.lingo.rest"), @ComponentScan("cz.cvut.fel.ear.lingo.security"),
        @ComponentScan("cz.cvut.fel.ear.lingo.services")})
public class LingoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LingoApplication.class, args);
    }
}
