package cl.smartjob.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

;

/**
 * The type Password config.
 */
@Configuration
public class PasswordConfig {
    @Value("${user.password.regex}")
    private String passwordRegex;

    /**
     * Gets password regex.
     *
     * @return the password regex
     */
    @Bean
    public String getPasswordRegex() {
        return passwordRegex;
    }
}
