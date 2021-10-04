package com.example.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = {"com.example.solution"})
@EnableSwagger2
public class SolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionApplication.class, args);
    }

}
