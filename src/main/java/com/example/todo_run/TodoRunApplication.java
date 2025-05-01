package com.example.todo_run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoRunApplication.class, args);
    }

}
