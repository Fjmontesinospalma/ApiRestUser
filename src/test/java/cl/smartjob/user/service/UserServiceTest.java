package cl.smartjob.user.service;

import cl.smartjob.user.dto.UserRequestDTO;
import cl.smartjob.user.dto.UserResponseDTO;
import cl.smartjob.user.exception.CustomException;
import cl.smartjob.user.model.User;
import cl.smartjob.user.repository.UserRepository;
import cl.smartjob.user.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    @Mock
    private UserRepository userRepository;

    @Mock
    private Util util;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void registrarUsuarioEmail() {
        // Arrange
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setEmail("test@example.com");


        UserResponseDTO responseDTO = userService.save(requestDTO);

        assertNotNull(responseDTO.getCreated());

    }

    @Test
    void RegistraUsuarioCorreoNoExiste() {

        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Francisco Montesinos");
        requestDTO.setEmail("fjmontesinospalma@gmail.com");
        requestDTO.setPassword("Password123");
        requestDTO.setPhones(new ArrayList<>());

        UserResponseDTO responseDTO = userService.save(requestDTO);

        // Assert
        assertNotNull(responseDTO);


    }
}
