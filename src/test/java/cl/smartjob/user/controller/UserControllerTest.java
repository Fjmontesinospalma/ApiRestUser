package cl.smartjob.user.controller;


import cl.smartjob.user.dto.UserRequestDTO;
import cl.smartjob.user.dto.UserResponseDTO;
import cl.smartjob.user.exception.CustomException;
import cl.smartjob.user.service.UserService;
import cl.smartjob.user.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registroUsuarioValido() throws Exception {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Francisco Montesinos");
        requestDTO.setEmail("fjmontesinospalma@gmail.com");
        requestDTO.setPassword("Password123");

        Mockito.when(userService.save(Mockito.any(UserRequestDTO.class))).thenReturn(new UserResponseDTO());

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void registraUsuarioEmailExiste() throws Exception {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Francisco Montesinos");
        requestDTO.setEmail("fjmontesinospalma@gmail.com");
        requestDTO.setPassword("Password123");

        Mockito.when(userService.save(Mockito.any(UserRequestDTO.class))).thenThrow(new CustomException("Email ya esta registrado."));

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }
}
