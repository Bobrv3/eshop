package com.bobrov.eshop.config;

import com.bobrov.eshop.dao.UserRepository;
import com.bobrov.eshop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DataConfig {
    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {
            userRepository.save(new User(1L, "admin", "$2a$12$YhY/MmkgGspmZi6P.7fG5O04OISr7C1.hlEs7TNlZO1cro90wn5Re", "+375447931700", "admin@gmail.com", User.Role.ROLE_ADMIN, false, true));
            userRepository.save(new User(2L, "user", "$2a$12$oS.MoiwXtb8YQjxrLNuBPubtc3QmOxzTM53YOZS6r.3zEvVd.ZqfK", "+375443875454", "user@gmail.com", User.Role.ROLE_USER, false, true));
        };
    }
}
