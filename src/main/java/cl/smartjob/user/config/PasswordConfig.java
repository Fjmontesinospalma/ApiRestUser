package cl.smartjob.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

;

@Configuration
public class PasswordConfig {
    @Value("${user.password.regex}")
    private String passwordRegex;

    @Bean
    public String getPasswordRegex() {
        return passwordRegex;
    }
}
