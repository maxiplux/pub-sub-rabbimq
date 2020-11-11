package com.juanfrancisco.pubsub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class PubsubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubsubApplication.class, args);
    }

}
