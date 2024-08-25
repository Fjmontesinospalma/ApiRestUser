package cl.smartjob.user.util;

import cl.smartjob.user.config.PasswordConfig;
import cl.smartjob.user.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * The type Util.
 */
@Component
public class Util {

    private final PasswordConfig passwordConfig;

    /**
     * Instantiates a new Util.
     *
     * @param passwordConfig the password config
     */
    @Autowired
    public Util(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }

    /**
     * Validate password.
     *
     * @param password the password
     */
    public void validatePassword(String password) {
        String passwordRegex = passwordConfig.getPasswordRegex();
        if (!Pattern.matches(passwordRegex, password)) {
            throw new CustomException("La clave no cumple con el formato requerido.");
        }
    }
}

