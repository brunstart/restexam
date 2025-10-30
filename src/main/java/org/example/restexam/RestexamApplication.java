package org.example.restexam;

import org.example.restexam.Repository.UserRepository;
import org.example.restexam.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RestexamApplication {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            List<User> all = userRepository.findAll();
            for(User u : all) {
                System.out.println(u);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RestexamApplication.class, args);
    }

}
