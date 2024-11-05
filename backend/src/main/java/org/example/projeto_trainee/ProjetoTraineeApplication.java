package org.example.projeto_trainee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjetoTraineeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoTraineeApplication.class, args);
    }

}
