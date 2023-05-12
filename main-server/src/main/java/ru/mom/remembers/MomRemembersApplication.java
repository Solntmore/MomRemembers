package ru.mom.remembers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MomRemembersApplication {
    public static void main(String[] args) {
        SpringApplication.run(MomRemembersApplication.class, args);
    }
}
