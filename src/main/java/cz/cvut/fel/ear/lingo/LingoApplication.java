package cz.cvut.fel.ear.lingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LingoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LingoApplication.class, args);
    }
}
