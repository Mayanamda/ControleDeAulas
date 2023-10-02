package com.falta.controledeaulas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.falta.controledeaulas.entity")
public class ControledeaulasApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControledeaulasApplication.class, args);
    }
}