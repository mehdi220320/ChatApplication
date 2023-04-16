package com.fsb.chatapplication;

import com.fsb.chatapplication.Models.User;
import com.fsb.chatapplication.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        User user=new User(1,"salim");
         return args -> {
            userRepository.save(user);
        };
    }
}
