package cl.smartjob.user.controller;

import cl.smartjob.user.dto.UserGetResponseDTO;
import cl.smartjob.user.dto.UserRequestDTO;
import cl.smartjob.user.dto.UserResponseDTO;
import cl.smartjob.user.exception.CustomException;
import cl.smartjob.user.exception.CustomResponse;
import cl.smartjob.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRequestDTO usuarioRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.save(usuarioRequestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
        } catch (CustomException e) {
            CustomResponse CustomResponse = new CustomResponse(e.getMessage());
            return new ResponseEntity<>(CustomResponse, HttpStatus.CONFLICT);
        } catch (Exception ex) {
            CustomResponse CustomResponse = new CustomResponse("Error al registrar usuario");
            return new ResponseEntity<>(CustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserGetResponseDTO>> getAllUsers() {
        List<UserGetResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        try {
            UserGetResponseDTO user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.update(id, userRequestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            CustomResponse response = new CustomResponse("Usuario eliminado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
